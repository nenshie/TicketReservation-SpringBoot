package com.nevena.dto.seat;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OccupiedSeatDto {
    private Long seatId;
    private int rowNumber;
    private int seatNumber;
    private boolean occupied;
}

