package com.nevena.dto.film;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class FilmCreateDto {
    @NotBlank
    private String title;

    @NotNull @Min(1)
    private Integer duration;

    @Size(max = 1024)
    private String posterUrl;

    private Long genreId;
    private Boolean active;
}
