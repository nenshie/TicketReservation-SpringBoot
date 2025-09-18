package com.nevena.service;

import com.nevena.dto.ProjectionDto;

import java.util.List;

public interface ProjectionService {

    List<ProjectionDto> getAllProjections();
    ProjectionDto getProjectionById(Long id);
    ProjectionDto createProjection(ProjectionDto projectionDto);
    ProjectionDto updateProjection(Long id, ProjectionDto projectionDto);
    void deleteProjection(Long id);
}
