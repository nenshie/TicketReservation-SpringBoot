package com.nevena.dto.reservation;

import com.nevena.dto.ticket.TicketResponseDto;
import com.nevena.entities.enums.ReservationStatus;
import lombok.*;

import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ReservationResponseDto {
    private Long reservationId;
    private ReservationStatus status;
    private Long userId;
    private List<TicketResponseDto> tickets;
    private boolean paid;
    private java.time.LocalDateTime paidAt;
    private String paymentProvider;
    private String paymentTransactionId;
    private java.time.LocalDateTime createdAt;
    private java.time.LocalDateTime updatedAt;
}
