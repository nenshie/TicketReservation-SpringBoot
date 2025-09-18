package com.nevena.service.impl;

import com.nevena.dto.FilmDto;
import com.nevena.entities.Film;
import com.nevena.mappers.FilmMapper;
import com.nevena.repository.FilmRepository;
import com.nevena.service.FilmService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilmServiceImpl implements FilmService {

    private final FilmRepository filmRepository;
    private final FilmMapper filmMapper;

    public FilmServiceImpl(FilmRepository filmRepository, FilmMapper filmMapper) {
        this.filmRepository = filmRepository;
        this.filmMapper = filmMapper;
    }

    @Override
    public List<FilmDto> getAllFilms() {
        return filmRepository.findAll()
                .stream()
                .map(filmMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public FilmDto getFilmById(Long id) {
        return filmRepository.findById(id)
                .map(filmMapper::toDTO)
                .orElse(null);
    }

    @Override
    public FilmDto createFilm(FilmDto filmDTO) {
        Film film = filmMapper.toEntity(filmDTO);
        Film saved = filmRepository.save(film);
        return filmMapper.toDTO(saved);
    }

    @Override
    public FilmDto updateFilm(Long id, FilmDto filmDTO) {
        return filmRepository.findById(id)
                .map(existing -> {
                    existing.setTitle(filmDTO.getTitle());
                    existing.setDuration(filmDTO.getDuration());
                    existing.setPosterUrl(filmDTO.getPosterUrl());
                    // set genre if needed
                    Film updated = filmRepository.save(existing);
                    return filmMapper.toDTO(updated);
                })
                .orElse(null);
    }

    @Override
    public void deleteFilm(Long id) {
        filmRepository.deleteById(id);
    }

}
