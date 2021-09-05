package com.zzb.common.productor;

import com.zzb.common.config.mq.RabbitMqConfig;
import com.zzb.service.RabbitMQService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

@Service
public class RabbitMQServiceImpl implements RabbitMQService {
    //日期格式化
    @Resource
    private RabbitTemplate rabbitTemplate;

    @Override
    public String sendMsg(String msg) {
        try {
            MessageProperties messageProperties = new MessageProperties();
            messageProperties.setMessageId(UUID.randomUUID().toString());
            messageProperties.setContentType("text/plain");
            messageProperties.setContentEncoding("utf-8");
            Message message = new Message(msg.getBytes(), messageProperties);
            rabbitTemplate.send(RabbitMqConfig.RABBITMQ_DEMO_DIRECT_EXCHANGE,
                    RabbitMqConfig.RABBITMQ_DEMO_DIRECT_ROUTING, message);
            return "ok";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }
}