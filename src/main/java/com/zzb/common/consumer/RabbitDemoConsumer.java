package com.zzb.common.consumer;

import com.google.gson.Gson;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.zzb.common.config.mq.RabbitMqConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author monkey
 */
@Slf4j
@Component
public class RabbitDemoConsumer {

    @Resource
    private RedisTemplate redisTemplate;

    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = RabbitMqConfig.RABBITMQ_DEMO_TOPIC, durable = "true",
            autoDelete = "false"),
            exchange = @Exchange(value = RabbitMqConfig.RABBITMQ_DEMO_DIRECT_EXCHANGE, type = ExchangeTypes.TOPIC),
            key = RabbitMqConfig.RABBITMQ_DEMO_DIRECT_ROUTING
    ))
    public void process(Message message, Channel channel) throws IOException {
        try {
            String messageId = message.getMessageProperties().getMessageId();
            Long deliveryTag = message.getMessageProperties().getDeliveryTag();
            log.info("messageId:{},deliveryTag:{}",messageId,deliveryTag);
            String body = new String(message.getBody(), "utf-8");
            log.info("消息内容->>>>>>>:{}", body);
            if(null == messageId){
                channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
                return ;
            }
            if("0".equals(body)){
                throw new RuntimeException("0的消息消费异常");
            }
            Boolean flag = redisTemplate.hasKey(messageId);
            // 如果存在直接return
            if(Boolean.TRUE.equals(flag)){
                return;
            }
            log.info("【结束】:{}", body);
            // 不存在的话，存入全局唯一key值
            redisTemplate.opsForValue().set(messageId, "全局消息id唯一", 5, TimeUnit.SECONDS);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        }catch (Exception e){
            //捕获异常后，重新发送到指定队列，自动确认不抛出异常即为ack
            Integer retryCount;
            Map<String, Object> headers = message.getMessageProperties().getHeaders();
            if(!headers.containsKey("retry-count")){
                retryCount=1;
            }else {
                retryCount = (Integer)headers.get("retry-count");
            }
            // 判断是否满足最大重试次数(重试3次)
            if(retryCount++<3) {
                headers.put("retry-count",retryCount);
                // 重新入队,继续消费
                AMQP.BasicProperties basicProperties = new AMQP.BasicProperties().builder().contentType("text/plain")
                        .messageId(message.getMessageProperties().getMessageId()).headers(headers).build();
                channel.basicPublish(message.getMessageProperties().getReceivedExchange(),
                        message.getMessageProperties().getReceivedRoutingKey(), basicProperties,
                        message.getBody());
            }else{
                // 重试次数超过3次,手动确认这个三个
                channel.basicAck(message.getMessageProperties().getDeliveryTag(),true);
            }
        }
    }
}