package com.nevena.mappers;
import com.nevena.entities.Film;
import com.nevena.dto.FilmDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface FilmMapper {

    FilmMapper INSTANCE = Mappers.getMapper(FilmMapper.class);

    @Mapping(source = "genre.name", target = "genreName")
    FilmDto toDTO(Film film);

    @Mapping(source = "genreName", target = "genre.name")
    Film toEntity(FilmDto dto);
}