package com.nevena.service.impl;

import com.nevena.dto.genre.GenreResponseDto;
import com.nevena.mappers.GenreMapper;
import com.nevena.repository.GenreRepository;
import com.nevena.service.GenreService;
import com.nevena.service.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreRepository repo;
    private final GenreMapper mapper;

    @Override
    public GenreResponseDto get(Long id) {
        return repo.findById(id).map(mapper::toDto)
                .orElseThrow(() -> new NotFoundException("Genre not found"));
    }

    @Override
    public Page<GenreResponseDto> list(Pageable pageable) {
        return repo.findAll(pageable).map(mapper::toDto);
    }
}
