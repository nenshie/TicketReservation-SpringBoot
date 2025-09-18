package com.nevena.mappers;

import com.nevena.dto.ProjectionDto;
import com.nevena.entities.Projection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {FilmMapper.class, RoomMapper.class})
public interface ProjectionMapper {

    ProjectionMapper INSTANCE = Mappers.getMapper(ProjectionMapper.class);

    @Mapping(source = "film", target = "film")
    @Mapping(source = "room", target = "room")
    ProjectionDto toDto(Projection projection);

    @Mapping(source = "film", target = "film")
    @Mapping(source = "room", target = "room")
    Projection toEntity(ProjectionDto dto);
}
