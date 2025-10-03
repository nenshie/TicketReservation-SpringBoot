package com.nevena.controller;

import com.nevena.dto.projection.ProjectionCreateDto;
import com.nevena.dto.projection.ProjectionResponseDto;
import com.nevena.dto.projection.ProjectionUpdateDto;
import com.nevena.service.ProjectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/projections")
@RequiredArgsConstructor
@org.springframework.validation.annotation.Validated
public class ProjectionController {
    private final ProjectionService service;

    @PostMapping("/create-projection")
    public ProjectionResponseDto create(@RequestBody @jakarta.validation.Valid ProjectionCreateDto dto) {
        return service.create(dto);
    }

    @PutMapping
    public ProjectionResponseDto update(@RequestBody @jakarta.validation.Valid ProjectionUpdateDto dto) {
        return service.update(dto);
    }

    @GetMapping("/{id}")
    public ProjectionResponseDto get(@PathVariable Long id) {
        return service.get(id);
    }

    @GetMapping
    public Page<ProjectionResponseDto> list(Pageable pageable) {
        return service.list(pageable);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}

