package com.nevena.dto.film;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class FilmResponseDto {
    private Long filmId;
    private String title;
    private Integer duration;
    private String posterUrl;
    private Long genreId;
    private String genreName;
    private boolean active;
    private java.time.LocalDateTime createdAt;
    private java.time.LocalDateTime updatedAt;
}
