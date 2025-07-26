package com.example.notification_service.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    @NotNull(message = "Order id must not be null")
    private Long id;

    @NotNull(message = "Created date must not be null")
    private LocalDateTime createdAt;

    @NotNull(message = "Total amount must not be null")
    @Positive(message = "Total amount must be positive")
    private BigDecimal totalAmount;

    @NotEmpty(message = "Order items list must not be empty")
    @Valid
    private List<OrderItemDTO> items;
}
