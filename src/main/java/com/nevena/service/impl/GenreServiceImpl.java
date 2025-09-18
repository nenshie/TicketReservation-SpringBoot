package com.nevena.service.impl;

import com.nevena.dto.GenreDto;
import com.nevena.entities.Genre;
import com.nevena.repository.GenreRepository;
import com.nevena.service.GenreService;
import com.nevena.mappers.GenreMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;

    public GenreServiceImpl(GenreRepository genreRepository, GenreMapper genreMapper) {
        this.genreRepository = genreRepository;
        this.genreMapper = genreMapper;
    }

    @Override
    public List<GenreDto> getAllGenres() {
        return genreRepository.findAll().stream()
                .map(genreMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public GenreDto getGenreById(Long id) {
        return genreRepository.findById(id)
                .map(genreMapper::toDto)
                .orElse(null);
    }

    @Override
    public GenreDto createGenre(GenreDto genreDto) {
        Genre genre = genreMapper.toEntity(genreDto);
        return genreMapper.toDto(genreRepository.save(genre));
    }

    @Override
    public GenreDto updateGenre(Long id, GenreDto genreDto) {
        return genreRepository.findById(id)
                .map(existing -> {
                    existing.setName(genreDto.getName());
                    return genreMapper.toDto(genreRepository.save(existing));
                })
                .orElse(null);
    }

    @Override
    public void deleteGenre(Long id) {
        genreRepository.deleteById(id);
    }
}
