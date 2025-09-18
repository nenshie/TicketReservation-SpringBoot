package com.nevena.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class PaymentDto {

    private Long paymentId;
    private Long reservationId;
    private double amount;
    private String provider;
    private String transactionId;
    private LocalDateTime paidAt;
}
