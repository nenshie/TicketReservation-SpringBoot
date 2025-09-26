package com.nevena.service.impl;

import com.nevena.dto.film.FilmCreateDto;
import com.nevena.dto.film.FilmResponseDto;
import com.nevena.dto.film.FilmUpdateDto;
import com.nevena.entities.Film;
import com.nevena.mappers.FilmMapper;
import com.nevena.repository.FilmRepository;
import com.nevena.service.FilmService;
import com.nevena.service.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FilmServiceImpl implements FilmService {
    private final FilmRepository filmRepository;
    private final FilmMapper filmMapper;

    @Override
    @Transactional
    public FilmResponseDto create(FilmCreateDto dto) {
        Film entity = filmMapper.toEntity(dto);
        return filmMapper.toDto(filmRepository.save(entity));
    }

    @Override
    @Transactional
    public FilmResponseDto update(FilmUpdateDto dto) {
        Film entity = filmRepository.findById(dto.getFilmId())
                .orElseThrow(() -> new NotFoundException("Film not found"));
        filmMapper.update(entity, dto);
        return filmMapper.toDto(filmRepository.save(entity));
    }

    @Override
    public FilmResponseDto get(Long id) {
        return filmRepository.findById(id).map(filmMapper::toDto)
                .orElseThrow(() -> new NotFoundException("Film not found"));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        filmRepository.deleteById(id);
    }

    @Override
    public Page<FilmResponseDto> list(Pageable pageable, String filterBy, String filterValue) {
        if (filterBy != null && filterValue != null) {
            switch (filterBy) {
                case "genre" -> {
                    return filmRepository.findByGenreNameIgnoreCase(filterValue, pageable)
                            .map(filmMapper::toDto);
                }
                case "title" -> {
                    return filmRepository.findByTitleContainingIgnoreCase(filterValue, pageable)
                            .map(filmMapper::toDto);
                }
            }
        }
        return filmRepository.findAll(pageable).map(filmMapper::toDto);
    }


}
