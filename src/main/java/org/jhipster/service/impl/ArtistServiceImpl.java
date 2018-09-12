package org.jhipster.service.impl;

import org.jhipster.service.ArtistService;
import org.jhipster.domain.Artist;
import org.jhipster.repository.ArtistRepository;
import org.jhipster.service.dto.ArtistDTO;
import org.jhipster.service.mapper.ArtistMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Service Implementation for managing Artist.
 */
@Service
@Transactional
public class ArtistServiceImpl implements ArtistService {

    private final Logger log = LoggerFactory.getLogger(ArtistServiceImpl.class);

    private final ArtistRepository artistRepository;

    private final ArtistMapper artistMapper;

    public ArtistServiceImpl(ArtistRepository artistRepository, ArtistMapper artistMapper) {
        this.artistRepository = artistRepository;
        this.artistMapper = artistMapper;
    }

    /**
     * Save a artist.
     *
     * @param artistDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ArtistDTO save(ArtistDTO artistDTO) {
        log.debug("Request to save Artist : {}", artistDTO);
        Artist artist = artistMapper.toEntity(artistDTO);
        artist = artistRepository.save(artist);
        return artistMapper.toDto(artist);
    }

    /**
     * Get all the artists.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ArtistDTO> findAll() {
        log.debug("Request to get all Artists");
        return artistRepository.findAll().stream()
            .map(artistMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one artist by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ArtistDTO> findOne(Long id) {
        log.debug("Request to get Artist : {}", id);
        return artistRepository.findById(id)
            .map(artistMapper::toDto);
    }

    /**
     * Delete the artist by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Artist : {}", id);
        artistRepository.deleteById(id);
    }
}
