package org.jhipster.service;

import org.jhipster.service.dto.SongDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Song.
 */
public interface SongService {

    /**
     * Save a song.
     *
     * @param songDTO the entity to save
     * @return the persisted entity
     */
    SongDTO save(SongDTO songDTO);

    /**
     * Get all the songs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<SongDTO> findAll(Pageable pageable);


    /**
     * Get the "id" song.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<SongDTO> findOne(Long id);

    /**
     * Delete the "id" song.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
