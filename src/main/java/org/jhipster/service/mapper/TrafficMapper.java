package org.jhipster.service.mapper;

import org.jhipster.domain.*;
import org.jhipster.service.dto.TrafficDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Traffic and its DTO TrafficDTO.
 */
@Mapper(componentModel = "spring", uses = {SongMapper.class})
public interface TrafficMapper extends EntityMapper<TrafficDTO, Traffic> {

    @Mapping(source = "song.id", target = "songId")
    TrafficDTO toDto(Traffic traffic);

    @Mapping(source = "songId", target = "song")
    Traffic toEntity(TrafficDTO trafficDTO);

    default Traffic fromId(Long id) {
        if (id == null) {
            return null;
        }
        Traffic traffic = new Traffic();
        traffic.setId(id);
        return traffic;
    }
}
