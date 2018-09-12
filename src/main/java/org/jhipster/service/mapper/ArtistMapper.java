package org.jhipster.service.mapper;

import org.jhipster.domain.*;
import org.jhipster.service.dto.ArtistDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Artist and its DTO ArtistDTO.
 */
@Mapper(componentModel = "spring", uses = {GenreMapper.class})
public interface ArtistMapper extends EntityMapper<ArtistDTO, Artist> {

    @Mapping(source = "genre.id", target = "genreId")
    ArtistDTO toDto(Artist artist);

    @Mapping(target = "artists", ignore = true)
    @Mapping(source = "genreId", target = "genre")
    Artist toEntity(ArtistDTO artistDTO);

    default Artist fromId(Long id) {
        if (id == null) {
            return null;
        }
        Artist artist = new Artist();
        artist.setId(id);
        return artist;
    }
}
