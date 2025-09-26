package com.nevena.dto.ticket;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class TicketCreateDto {
    @NotNull
    private Long projectionId;

    @NotNull
    private Long seatId;
}
