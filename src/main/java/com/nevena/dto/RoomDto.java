package com.nevena.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RoomDto {

    private Long roomId;
    private String name;
    private Integer numberOfRows;
    private Integer seatsPerRow;
}
