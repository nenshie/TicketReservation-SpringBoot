package com.nevena.service;

import com.nevena.dto.FilmDto;

import java.util.List;

public interface FilmService {

    List<FilmDto> getAllFilms();
    FilmDto getFilmById(Long id);
    FilmDto createFilm(FilmDto film);
    FilmDto updateFilm(Long id, FilmDto filmDTO);
    void deleteFilm(Long id);
}
