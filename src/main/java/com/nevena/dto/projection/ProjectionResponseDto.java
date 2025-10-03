package com.nevena.dto.projection;

import lombok.*;

import java.math.BigDecimal;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ProjectionResponseDto {
    private Long projectionId;
    private java.time.LocalDateTime dateTime;
    private Long filmId;
    private String title;
    private String posterUrl;
    private Integer duration;
    private Long genreId;
    private String genreName;
    private Long roomId;
    private String roomName;
    private Integer numberOfRows;
    private Integer seatsPerRow;
    private boolean active;
    private BigDecimal price;
    private java.time.LocalDateTime salesOpenUntil;
    private java.time.LocalDateTime createdAt;
    private java.time.LocalDateTime updatedAt;
}
