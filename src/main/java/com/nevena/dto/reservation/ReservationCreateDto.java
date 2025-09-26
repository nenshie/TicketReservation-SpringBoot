package com.nevena.dto.reservation;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ReservationCreateDto {
    @NotNull
    private Long ticketId;
}
