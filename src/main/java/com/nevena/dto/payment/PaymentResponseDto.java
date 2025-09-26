package com.nevena.dto.payment;

import com.nevena.entities.enums.PaymentStatus;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PaymentResponseDto {
    private Long paymentId;
    private Long reservationId;
    private double amount;
    private String provider;
    private PaymentStatus status;
    private String currency;
    private String transactionId;
    private String providerRef;
    private String errorCode;
    private String errorMessage;
    private java.time.LocalDateTime paidAt;
    private java.time.LocalDateTime createdAt;
    private java.time.LocalDateTime updatedAt;
}
