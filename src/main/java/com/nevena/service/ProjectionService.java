package com.nevena.service;

import com.nevena.dto.projection.ProjectionCreateDto;
import com.nevena.dto.projection.ProjectionResponseDto;
import com.nevena.dto.projection.ProjectionUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProjectionService {
    ProjectionResponseDto create(ProjectionCreateDto dto);
    ProjectionResponseDto update(ProjectionUpdateDto dto);
    ProjectionResponseDto get(Long id);
    void delete(Long id);
    Page<ProjectionResponseDto> list(Pageable pageable);
}
