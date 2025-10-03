package com.nevena.mappers;

import com.nevena.dto.film.FilmCreateDto;
import com.nevena.dto.film.FilmResponseDto;
import com.nevena.dto.film.FilmUpdateDto;
import com.nevena.entities.Film;
import com.nevena.mappers.config.CentralMapperConfig;
import org.mapstruct.*;

@Mapper(config = CentralMapperConfig.class, uses = {IdResolvers.class})
public interface FilmMapper {
    default com.nevena.entities.Genre map(java.lang.Long id) {
        if (id == null) return null;
        com.nevena.entities.Genre g = new com.nevena.entities.Genre();
        g.setGenreId(id);
        return g;
    }

    @Mapping(target = "filmId", ignore = true)
    @Mapping(target = "genre", source = "genreId")
    Film toEntity(FilmCreateDto dto);


    @BeanMapping(
            ignoreByDefault = true,
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
    )
    @Mapping(target = "filmId", source = "filmId")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "duration", source = "duration")
    @Mapping(target = "posterUrl", source = "posterUrl")
    @Mapping(target = "active", source = "active")
    @Mapping(target = "genre", source = "genreId")
    void update(@MappingTarget Film entity, FilmUpdateDto dto);

    // Response: flatten genre
    @Mapping(target = "genreId", source = "genre.genreId")
    @Mapping(target = "genreName", source = "genre.name")
    FilmResponseDto toDto(Film entity);
}
