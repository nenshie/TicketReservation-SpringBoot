package com.nevena.service.impl;

import com.nevena.dto.projection.ProjectionCreateDto;
import com.nevena.dto.projection.ProjectionResponseDto;
import com.nevena.dto.projection.ProjectionUpdateDto;
import com.nevena.entities.Projection;
import com.nevena.mappers.ProjectionMapper;
import com.nevena.repository.ProjectionRepository;
import com.nevena.service.ProjectionService;
import com.nevena.service.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ProjectionServiceImpl implements ProjectionService {

    private final ProjectionRepository repo;
    private final ProjectionMapper mapper;

    @Override
    @Transactional
    public ProjectionResponseDto create(ProjectionCreateDto dto) {
        Projection entity = mapper.toEntity(dto);
        entity.setPrice(BigDecimal.valueOf(550));
        entity.setSalesOpenUntil(dto.getDateTime());
        return mapper.toDto(repo.save(entity));
    }

    @Override
    @Transactional
    public ProjectionResponseDto update(ProjectionUpdateDto dto) {
        Projection entity = repo.findById(dto.getProjectionId())
                .orElseThrow(() -> new NotFoundException("Projection not found"));
        mapper.update(entity, dto);
        return mapper.toDto(repo.save(entity));
    }

    @Override
    public ProjectionResponseDto get(Long id) {
        return repo.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new NotFoundException("Projection not found"));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repo.deleteById(id);
    }

    @Override
    public Page<ProjectionResponseDto> list(Pageable pageable) {
        return repo.findAll(pageable).map(mapper::toDto);
    }
}
