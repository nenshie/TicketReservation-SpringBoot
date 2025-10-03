package com.nevena.dto.reservation;

import com.nevena.dto.seat.SeatDto;
import lombok.*;

import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class MakeReservationDto {
    private String userId;
    private Long projectionId;
    private List<SeatDto> seats;
}
