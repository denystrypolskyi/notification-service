package com.example.notification_service.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String ORDER_NOTIFICATIONS_QUEUE = "order.notifications";
    public static final String ORDER_NOTIFICATIONS_DLQ = "order.notifications.dlq";

    @Bean
    Queue orderNotificationsQueue() {
        return QueueBuilder.durable(ORDER_NOTIFICATIONS_QUEUE)
                .deadLetterExchange("")
                .deadLetterRoutingKey(ORDER_NOTIFICATIONS_DLQ)
                .build();
    }

    @Bean
    Queue orderNotificationsDeadLetterQueue() {
        return QueueBuilder.durable(ORDER_NOTIFICATIONS_DLQ).build();
    }
}
