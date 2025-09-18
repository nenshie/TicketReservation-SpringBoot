package com.nevena.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class TicketDto {

    private Long ticketId;
    private String qrCode;
    private Long seatId;
    private Long projectionId;
    private LocalDateTime createdAt;
}
