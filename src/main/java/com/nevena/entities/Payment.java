package com.nevena.entities;

import com.nevena.entities.common.Auditable;
import com.nevena.entities.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(
        name = "Payment",
        indexes = @Index(name = "idx_payment_reservation", columnList = "reservationId"),
        uniqueConstraints = @UniqueConstraint(name = "uk_payment_tx", columnNames = {"transactionId"})
)
public class Payment extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    @ManyToOne
    @JoinColumn(name = "reservationId")
    private Reservation reservation;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    private String provider;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus status = PaymentStatus.INITIATED;

    @Column(nullable = false)
    private String currency = "RSD";

    @Column(nullable = false)
    private String transactionId;

    private String providerRef;      // gateway reference or mock
    @Lob
    private String rawResponse;      // mock gateway payload
    private String errorCode;
    private String errorMessage;
    private LocalDateTime paidAt;
}
