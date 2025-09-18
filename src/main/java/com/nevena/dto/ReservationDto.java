package com.nevena.dto;

import com.nevena.entities.enums.ReservationStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class ReservationDto {

    private Long reservationId;
    private ReservationStatus status;
    private Long userId;
    private Long ticketId;
    private boolean paid;
    private LocalDateTime paidAt;
}
