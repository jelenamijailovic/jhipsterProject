package org.jhipster.service.impl;

import org.jhipster.service.SongService;
import org.jhipster.domain.Song;
import org.jhipster.repository.SongRepository;
import org.jhipster.service.dto.SongDTO;
import org.jhipster.service.mapper.SongMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing Song.
 */
@Service
@Transactional
public class SongServiceImpl implements SongService {

    private final Logger log = LoggerFactory.getLogger(SongServiceImpl.class);

    private final SongRepository songRepository;

    private final SongMapper songMapper;

    public SongServiceImpl(SongRepository songRepository, SongMapper songMapper) {
        this.songRepository = songRepository;
        this.songMapper = songMapper;
    }

    /**
     * Save a song.
     *
     * @param songDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public SongDTO save(SongDTO songDTO) {
        log.debug("Request to save Song : {}", songDTO);
        Song song = songMapper.toEntity(songDTO);
        song = songRepository.save(song);
        return songMapper.toDto(song);
    }

    /**
     * Get all the songs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SongDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Songs");
        return songRepository.findAll(pageable)
            .map(songMapper::toDto);
    }


    /**
     * Get one song by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SongDTO> findOne(Long id) {
        log.debug("Request to get Song : {}", id);
        return songRepository.findById(id)
            .map(songMapper::toDto);
    }

    /**
     * Delete the song by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Song : {}", id);
        songRepository.deleteById(id);
    }
}
