package com.nevena.dto.film;

import lombok.*;
import jakarta.validation.constraints.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class FilmUpdateDto {
    @NotNull
    private Long filmId;
    private String title;
    private Integer duration;
    private String posterUrl;
    private Long genreId;
    private Boolean active;
}
