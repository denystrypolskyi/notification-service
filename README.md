# Notification Service

Notification service for the e-commerce backend. It consumes order notification messages from RabbitMQ and sends order confirmation emails.

## Tech Stack

- Java 21
- Spring Boot
- Spring AMQP
- RabbitMQ
- Spring Mail
- Bean Validation
- JUnit, Mockito

## Messaging

The service listens to this RabbitMQ queue:

```text
order.notifications
```

Failed messages are rejected without requeueing and routed to the dead-letter queue:

```text
order.notifications.dlq
```

### Message Shape

```json
{
  "email": "buyer@example.com",
  "order": {
    "id": 1,
    "userId": 7,
    "createdAt": "2026-07-05T12:00:00",
    "status": "CREATED",
    "totalAmount": 199.98,
    "items": [
      {
        "id": 1,
        "productId": 10,
        "productName": "Keyboard",
        "description": "Mechanical keyboard",
        "quantity": 2,
        "pricePerUnit": 99.99
      }
    ]
  }
}
```

## Configuration

Mail credentials are configured through environment variables:

```text
MAIL_USERNAME
MAIL_PASSWORD
```

RabbitMQ settings are provided through Spring configuration or Docker Compose environment variables.

## Run Tests

```powershell
.\mvnw.cmd test
```
