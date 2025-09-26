package com.nevena.entities;

import com.nevena.entities.common.Auditable;
import com.nevena.entities.enums.ReservationStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "Reservation")
public class Reservation extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReservationStatus status = ReservationStatus.RESERVED;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    // Single-seat reservation model (adjust to OneToMany<List<Ticket>> if needed)
    @OneToOne
    @JoinColumn(name = "ticketId", unique = true)
    private Ticket ticket;

    @Column(nullable = false)
    private boolean paid = false;

    private LocalDateTime paidAt;
    private String paymentProvider;
    private String paymentTransactionId;

    private LocalDateTime canceledAt;

    @Version
    private Long version;
}
