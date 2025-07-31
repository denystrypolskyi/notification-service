# Notification Service

This microservice handles sending email notifications for order-related events. It listens to RabbitMQ messages and sends emails when a new order is created.

## 🚀 Features

- Listens to RabbitMQ queue for order notifications  
- Sends email notifications when a new order is created  
- Formats order details including items, quantities, and total amount in the email  

## 📨 Email Notification

- Sends a simple email with order details to the specified email address  
- Email includes order ID, creation time, item list, and total amount  

## 🐇 Messaging

- Uses RabbitMQ with a topic exchange (`order-exchange`) and a queue (`order.notifications`)  
- Listens on routing keys matching `order.notifications.#`  

## 📦 Tech Stack

- Java 24  
- Spring Boot  
- RabbitMQ (spring-amqp)  
- Jakarta Validation  
- Lombok  
- JavaMailSender  

## How It Works

1. The service listens for messages on the RabbitMQ `order.notifications` queue.  
2. When a message arrives, it is deserialized into an `OrderNotificationDTO`.  
3. The `EmailService` sends an email with the order details to the recipient email address.  
