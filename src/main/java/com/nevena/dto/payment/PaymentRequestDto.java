package com.nevena.dto.payment;

import lombok.Data;

@Data
public class PaymentRequestDto {
    private String cardNumber;
    private String expiryDate; // MM/YY
    private String cvv;
}