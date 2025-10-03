package com.nevena.dto.ticket;

import com.nevena.entities.enums.ReservationStatus;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class TicketResponseDto {
    private Long ticketId;
    private String publicCode;
    private ReservationStatus status;
    private Long projectionId;
    private String filmTitle;
    private Long roomId;
    private String roomName;
    private Long seatId;
    private Integer seatRow;
    private Integer seatNumber;
    private String qrCode;
    private java.time.LocalDateTime verifiedAt;
    private Long verifiedByUserId;
    private java.time.LocalDateTime createdAt;
    private java.time.LocalDateTime updatedAt;
}
