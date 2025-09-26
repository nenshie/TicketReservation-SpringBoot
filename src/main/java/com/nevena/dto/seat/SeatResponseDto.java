package com.nevena.dto.seat;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class SeatResponseDto {
    private Long seatId;
    private Long roomId;
    private String roomName;
    private Integer rowNumber;
    private Integer seatNumber;
    private java.time.LocalDateTime createdAt;
    private java.time.LocalDateTime updatedAt;
}
