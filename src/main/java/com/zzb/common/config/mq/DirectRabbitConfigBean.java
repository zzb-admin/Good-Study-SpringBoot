package com.zzb.common.config.mq;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


/**
 * @Description: 配置生产或消费端启动生成队列
 * 实现BeanPostProcessor类，使用Bean的生命周期函数
 * @author: monkey.zhao
 **/
@Component
public class DirectRabbitConfigBean implements BeanPostProcessor {
    //这是创建交换机和队列用的rabbitAdmin对象
    @Resource
    private RabbitAdmin rabbitAdmin;

    //初始化rabbitAdmin对象
    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        // 只有设置为 true，spring 才会加载 RabbitAdmin 这个类
        rabbitAdmin.setAutoStartup(true);
        return rabbitAdmin;
    }

    //实例化bean后，也就是Bean的后置处理器
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        //创建交换机
        rabbitAdmin.declareExchange(DirectRabbitConfig.rabbitmqDemoDirectExchange());
        //创建队列
        rabbitAdmin.declareQueue(DirectRabbitConfig.rabbitmqDemoDirectQueue());
        return null;
    }
}