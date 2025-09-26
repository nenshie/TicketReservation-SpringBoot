package com.nevena.dto.genre;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class GenreResponseDto {
    private Long genreId;
    private String name;
    private java.time.LocalDateTime createdAt;
    private java.time.LocalDateTime updatedAt;
}
