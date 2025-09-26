package com.nevena.mappers;

import com.nevena.dto.projection.ProjectionCreateDto;
import com.nevena.dto.projection.ProjectionResponseDto;
import com.nevena.dto.projection.ProjectionUpdateDto;
import com.nevena.entities.Projection;
import com.nevena.mappers.config.CentralMapperConfig;
import org.mapstruct.*;

@Mapper(config = CentralMapperConfig.class, uses = {IdResolvers.class})
public interface ProjectionMapper {

    default com.nevena.entities.Film map(java.lang.Long id) {
        if (id == null) return null;
        com.nevena.entities.Film f = new com.nevena.entities.Film();
        f.setFilmId(id);
        return f;
    }

    default com.nevena.entities.Room mapRoom(java.lang.Long id) {
        if (id == null) return null;
        com.nevena.entities.Room r = new com.nevena.entities.Room();
        r.setRoomId(id);
        return r;
    }


    @Mapping(target = "projectionId", ignore = true)
    @Mapping(target = "film", source = "filmId")
    @Mapping(target = "room", source = "roomId")
    Projection toEntity(ProjectionCreateDto dto);

    @BeanMapping(
            ignoreByDefault = true,
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
    )
    @Mapping(target = "projectionId", source = "projectionId")
    @Mapping(target = "dateTime", source = "dateTime")
    @Mapping(target = "film", source = "filmId")
    @Mapping(target = "room", source = "roomId")
    @Mapping(target = "active", source = "active")
    @Mapping(target = "salesOpenUntil", source = "salesOpenUntil")
    void update(@MappingTarget Projection entity, ProjectionUpdateDto dto);

    @Mapping(target = "filmId", source = "film.filmId")
    @Mapping(target = "title", source = "film.title")
    @Mapping(target = "posterUrl", source = "film.posterUrl")
    @Mapping(target = "duration", source = "film.duration")
    @Mapping(target = "genreId", source = "film.genre.genreId")
    @Mapping(target = "genreName", source = "film.genre.name")
    @Mapping(target = "roomId", source = "room.roomId")
    @Mapping(target = "roomName", source = "room.name")
    @Mapping(target = "active", source = "active")
    @Mapping(target = "salesOpenUntil", source = "salesOpenUntil")
    ProjectionResponseDto toDto(Projection entity);
}

