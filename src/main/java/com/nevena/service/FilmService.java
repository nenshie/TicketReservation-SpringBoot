package com.nevena.service;

import com.nevena.dto.film.FilmCreateDto;
import com.nevena.dto.film.FilmResponseDto;
import com.nevena.dto.film.FilmUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FilmService {
    FilmResponseDto create(FilmCreateDto dto);
    FilmResponseDto update(FilmUpdateDto dto);
    FilmResponseDto get(Long id);
    void delete(Long id);
    Page<FilmResponseDto> list(Pageable pageable, String filterBy, String filterValue);
}
