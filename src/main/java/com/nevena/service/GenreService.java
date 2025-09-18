package com.nevena.service;

import com.nevena.dto.GenreDto;

import java.util.List;

public interface GenreService {

    List<GenreDto> getAllGenres();
    GenreDto getGenreById(Long id);
    GenreDto createGenre(GenreDto genreDto);
    GenreDto updateGenre(Long id, GenreDto genreDto);
    void deleteGenre(Long id);
}
