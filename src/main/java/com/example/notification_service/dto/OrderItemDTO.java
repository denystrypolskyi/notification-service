package com.example.notification_service.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDTO {

    @NotNull(message = "Product ID must not be null")
    private Long productId;

    @NotBlank(message = "Product name must not be blank")
    private String productName;

    @Positive(message = "Quantity must be greater than zero")
    private int quantity;

    @NotNull(message = "Price per unit must not be null")
    @Positive(message = "Price per unit must be greater than zero")
    private BigDecimal pricePerUnit;
}
