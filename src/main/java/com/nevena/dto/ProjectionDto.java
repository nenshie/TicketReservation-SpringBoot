package com.nevena.dto;

import com.nevena.entities.Room;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class ProjectionDto {

    private Long projectionId;
    private LocalDateTime dateTime;
    private FilmDto film;
    private String filmTitle;
    private RoomDto room;
    private String roomName;
}
