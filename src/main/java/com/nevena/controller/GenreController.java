package com.nevena.controller;

import com.nevena.dto.genre.GenreResponseDto;
import com.nevena.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/genres")
@RequiredArgsConstructor
public class GenreController {

    private final GenreService service;

    @GetMapping
    public ResponseEntity<Page<GenreResponseDto>> list(Pageable pageable) {
        return ResponseEntity.ok(service.list(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenreResponseDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.get(id));
    }
}
