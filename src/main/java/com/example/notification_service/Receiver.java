package com.example.notification_service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.example.notification_service.config.RabbitConfig;
import com.example.notification_service.dto.OrderNotificationDTO;
import com.example.notification_service.service.EmailService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class Receiver {

    private static final Logger log = LoggerFactory.getLogger(Receiver.class);

    private final EmailService emailService;
    private final ObjectMapper objectMapper;

    @RabbitListener(queues = RabbitConfig.ORDER_NOTIFICATIONS_QUEUE)
    public void receiveOrderMessage(String messageJson) {
        try {
            OrderNotificationDTO notification = objectMapper.readValue(messageJson, OrderNotificationDTO.class);
            log.info("Received order notification for {}", notification.getEmail());

            emailService.sendOrderCreatedEmail(notification.getEmail(), notification.getOrder());
        } catch (Exception e) {
            log.error("Failed to process order notification message", e);
            throw new AmqpRejectAndDontRequeueException("Failed to process order notification", e);
        }
    }
}
