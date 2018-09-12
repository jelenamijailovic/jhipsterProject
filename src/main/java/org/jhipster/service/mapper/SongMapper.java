package org.jhipster.service.mapper;

import org.jhipster.domain.*;
import org.jhipster.service.dto.SongDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Song and its DTO SongDTO.
 */
@Mapper(componentModel = "spring", uses = {ArtistMapper.class, PriceMapper.class})
public interface SongMapper extends EntityMapper<SongDTO, Song> {

    @Mapping(source = "artist.id", target = "artistId")
    @Mapping(source = "price.id", target = "priceId")
    SongDTO toDto(Song song);

    @Mapping(target = "songs", ignore = true)
    @Mapping(source = "artistId", target = "artist")
    @Mapping(source = "priceId", target = "price")
    Song toEntity(SongDTO songDTO);

    default Song fromId(Long id) {
        if (id == null) {
            return null;
        }
        Song song = new Song();
        song.setId(id);
        return song;
    }
}
