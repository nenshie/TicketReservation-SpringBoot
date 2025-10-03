package com.nevena.entities;

import com.nevena.entities.common.Auditable;
import com.nevena.entities.enums.ReservationStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(
        name = "Ticket",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_public_code", columnNames = {"publicCode"}),
                @UniqueConstraint(name = "uk_projection_seat", columnNames = {"projectionId", "seatId"})
        },
        indexes = {
                @Index(name = "idx_ticket_projection", columnList = "projectionId"),
                @Index(name = "idx_ticket_seat", columnList = "seatId"),
                @Index(name = "idx_ticket_status", columnList = "status")
        }
)
public class Ticket extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticketId", nullable = false)
    private Long ticketId;

    // QR should encode this publicCode, not the DB ID
    @Column(nullable = false, unique = true, length = 64)
    private String publicCode = UUID.randomUUID().toString();

    @Column(name = "qr_code", columnDefinition = "VARCHAR(MAX)")
    private String qrCode;

    @Column(name = "price")
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReservationStatus status = ReservationStatus.CREATED;

    @ManyToOne
    @JoinColumn(name = "seatId")
    private Seat seat;

    @ManyToOne
    @JoinColumn(name = "projectionId")
    private Projection projection;

    private LocalDateTime verifiedAt;
    private Long verifiedByUserId;

    @Version
    private Long version;

    @ManyToOne
    @JoinColumn(name = "reservationId")
    private Reservation reservation;
}
