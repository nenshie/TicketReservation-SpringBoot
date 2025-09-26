package com.nevena.service;

import com.nevena.dto.genre.GenreResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GenreService {
    GenreResponseDto get(Long id);
    Page<GenreResponseDto> list(Pageable pageable);
}
