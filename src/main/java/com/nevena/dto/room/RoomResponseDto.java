package com.nevena.dto.room;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class RoomResponseDto {
    private Long roomId;
    private String name;
    private Integer numberOfRows;
    private Integer seatsPerRow;
    private java.time.LocalDateTime createdAt;
    private java.time.LocalDateTime updatedAt;
}
