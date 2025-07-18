package com.example.notification_service;

import java.util.concurrent.CountDownLatch;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    private CountDownLatch latch = new CountDownLatch(1);

    @RabbitListener(queues = "order.notifications")
    public void receiveOrderMessage(String message) {
        System.out.println("Received order message: " + message);
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }

}