package com.zzb.common.config.mq;

/**
 * @program: Good-Study-SpringBoot
 * @description: ranbbitMq配置类
 * @author: monkey.zhao
 * @create: 2021-08-31 11:17
 **/
public class RabbitMqConfig {

    // 队列
    public static final String RABBITMQ_DEMO_TOPIC = "queue:queue001";

    // 交换机
    public static final String RABBITMQ_DEMO_DIRECT_EXCHANGE = "exchange:exchanage001";

    // 绑定路由key
    public static final String RABBITMQ_DEMO_DIRECT_ROUTING = "routing:routing001";
}
