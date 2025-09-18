package com.nevena.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class SeatDto {

    private Long seatId;
    private Integer seatNumber;
    private Integer rowNumber;
    private RoomDto room;
}
