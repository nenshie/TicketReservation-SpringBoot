package com.nevena.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FilmDto {

    private Long filmId;
    private String title;
    private Integer duration;
    private String posterUrl;
    private String genreName;
}
