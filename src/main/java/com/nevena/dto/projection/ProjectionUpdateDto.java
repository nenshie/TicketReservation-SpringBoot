package com.nevena.dto.projection;

import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ProjectionUpdateDto {
    @NotNull
    private Long projectionId;

    private LocalDateTime dateTime;
    private Long filmId;
    private Long roomId;
    private Boolean active;
    private LocalDateTime salesOpenUntil;
}
