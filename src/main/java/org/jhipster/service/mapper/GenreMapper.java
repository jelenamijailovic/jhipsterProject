package org.jhipster.service.mapper;

import org.jhipster.domain.*;
import org.jhipster.service.dto.GenreDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Genre and its DTO GenreDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface GenreMapper extends EntityMapper<GenreDTO, Genre> {


    @Mapping(target = "genres", ignore = true)
    Genre toEntity(GenreDTO genreDTO);

    default Genre fromId(Long id) {
        if (id == null) {
            return null;
        }
        Genre genre = new Genre();
        genre.setId(id);
        return genre;
    }
}
