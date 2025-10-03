package com.nevena.dto.seat;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeatDto {
    private String id;
    private int rowNumber;
    private int seatNumber;
    private int roomId;
}
