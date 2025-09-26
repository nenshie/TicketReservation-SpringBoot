package com.nevena.dto.payment;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PaymentCreateDto {
    @NotNull
    private Long reservationId;

    @NotNull @Positive
    private double amount;

    @NotBlank
    private String provider;
}
