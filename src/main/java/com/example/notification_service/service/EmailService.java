package com.example.notification_service.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.notification_service.dto.OrderDTO;
import com.example.notification_service.dto.OrderItemDTO;

@Service
public class EmailService {
    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendOrderCreatedEmail(String toEmail, OrderDTO order) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(toEmail);
        mailMessage.setSubject("New Order Created: #" + order.getId());

        StringBuilder sb = new StringBuilder();
        sb.append("A new order has been placed:\n\n");
        sb.append("Order ID: ").append(order.getId()).append("\n");
        sb.append("Created At: ").append(order.getCreatedAt()).append("\n\n");
        sb.append("Items:\n");

        for (OrderItemDTO item : order.getItems()) {
            sb.append("- ")
                    .append(item.getProductName())
                    .append(" x ")
                    .append(item.getQuantity())
                    .append(" @ ")
                    .append(item.getPricePerUnit())
                    .append("\n");
        }

        sb.append("\nTotal Amount: ").append(order.getTotalAmount()).append("\n");

        mailMessage.setText(sb.toString());

        mailSender.send(mailMessage);
    }
}
