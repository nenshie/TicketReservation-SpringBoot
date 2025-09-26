package com.nevena.dto.projection;

import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ProjectionCreateDto {
    @NotNull
    private LocalDateTime dateTime;

    @NotNull
    private Long filmId;

    @NotNull
    private Long roomId;

    private Boolean active;
    private LocalDateTime salesOpenUntil;
}
