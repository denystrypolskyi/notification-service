package com.example.notification_service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.example.notification_service.dto.OrderNotificationDTO;
import com.example.notification_service.service.EmailService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class Receiver {

    private final EmailService emailService;
    private final ObjectMapper objectMapper;

    @RabbitListener(queues = "order.notifications")
    public void receiveOrderMessage(String messageJson) {
        try {
            OrderNotificationDTO notification = objectMapper.readValue(messageJson, OrderNotificationDTO.class);
            System.out.println("Received order notification: " + notification);

            emailService.sendOrderCreatedEmail(notification.getEmail(), notification.getOrder());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
