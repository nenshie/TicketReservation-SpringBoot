package com.nevena.services.impl;

import com.nevena.dto.ProjectionDto;
import com.nevena.entities.Projection;
import com.nevena.mappers.FilmMapper;
import com.nevena.mappers.ProjectionMapper;
import com.nevena.mappers.RoomMapper;
import com.nevena.repository.ProjectionRepository;
import com.nevena.service.ProjectionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectionServiceImpl implements ProjectionService {

    private final ProjectionRepository projectionRepository;
    private final ProjectionMapper projectionMapper = ProjectionMapper.INSTANCE;
    private final FilmMapper filmMapper = FilmMapper.INSTANCE;
    private final RoomMapper roomMapper = RoomMapper.INSTANCE;

    public ProjectionServiceImpl(ProjectionRepository projectionRepository) {
        this.projectionRepository = projectionRepository;
    }

    @Override
    public List<ProjectionDto> getAllProjections() {
        return projectionRepository.findAll()
                .stream()
                .map(projectionMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProjectionDto getProjectionById(Long id) {
        return projectionRepository.findById(id)
                .map(projectionMapper::toDto)
                .orElse(null);
    }

    @Override
    public ProjectionDto createProjection(ProjectionDto projectionDto) {
        Projection projection = projectionMapper.toEntity(projectionDto);
        return projectionMapper.toDto(projectionRepository.save(projection));
    }

    @Override
    public ProjectionDto updateProjection(Long id, ProjectionDto projectionDto) {
        return projectionRepository.findById(id)
                .map(existing -> {
                    existing.setFilm(filmMapper.toEntity(projectionDto.getFilm()));
                    existing.setRoom(roomMapper.toEntity(projectionDto.getRoom()));
                    existing.setDateTime(projectionDto.getDateTime());
                    return projectionMapper.toDto(projectionRepository.save(existing));
                }).orElse(null);
    }

    @Override
    public void deleteProjection(Long id) {
        projectionRepository.deleteById(id);
    }
}
