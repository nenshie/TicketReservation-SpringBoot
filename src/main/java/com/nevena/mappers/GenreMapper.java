package com.nevena.mappers;

import com.nevena.dto.genre.GenreResponseDto;
import com.nevena.entities.Genre;
import com.nevena.mappers.config.CentralMapperConfig;
import org.mapstruct.Mapper;

@Mapper(config = CentralMapperConfig.class)
public interface GenreMapper {
    GenreResponseDto toDto(Genre genre);
}
