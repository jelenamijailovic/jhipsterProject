package org.jhipster.web.rest;

import com.codahale.metrics.annotation.Timed;
import org.jhipster.service.SongService;
import org.jhipster.web.rest.errors.BadRequestAlertException;
import org.jhipster.web.rest.util.HeaderUtil;
import org.jhipster.web.rest.util.PaginationUtil;
import org.jhipster.service.dto.SongDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Song.
 */
@RestController
@RequestMapping("/api")
public class SongResource {

    private final Logger log = LoggerFactory.getLogger(SongResource.class);

    private static final String ENTITY_NAME = "song";

    private final SongService songService;

    public SongResource(SongService songService) {
        this.songService = songService;
    }

    /**
     * POST  /songs : Create a new song.
     *
     * @param songDTO the songDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new songDTO, or with status 400 (Bad Request) if the song has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/songs")
    @Timed
    public ResponseEntity<SongDTO> createSong(@RequestBody SongDTO songDTO) throws URISyntaxException {
        log.debug("REST request to save Song : {}", songDTO);
        if (songDTO.getId() != null) {
            throw new BadRequestAlertException("A new song cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SongDTO result = songService.save(songDTO);
        return ResponseEntity.created(new URI("/api/songs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /songs : Updates an existing song.
     *
     * @param songDTO the songDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated songDTO,
     * or with status 400 (Bad Request) if the songDTO is not valid,
     * or with status 500 (Internal Server Error) if the songDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/songs")
    @Timed
    public ResponseEntity<SongDTO> updateSong(@RequestBody SongDTO songDTO) throws URISyntaxException {
        log.debug("REST request to update Song : {}", songDTO);
        if (songDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SongDTO result = songService.save(songDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, songDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /songs : get all the songs.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of songs in body
     */
    @GetMapping("/songs")
    @Timed
    public ResponseEntity<List<SongDTO>> getAllSongs(Pageable pageable) {
        log.debug("REST request to get a page of Songs");
        Page<SongDTO> page = songService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/songs");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /songs/:id : get the "id" song.
     *
     * @param id the id of the songDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the songDTO, or with status 404 (Not Found)
     */
    @GetMapping("/songs/{id}")
    @Timed
    public ResponseEntity<SongDTO> getSong(@PathVariable Long id) {
        log.debug("REST request to get Song : {}", id);
        Optional<SongDTO> songDTO = songService.findOne(id);
        return ResponseUtil.wrapOrNotFound(songDTO);
    }

    /**
     * DELETE  /songs/:id : delete the "id" song.
     *
     * @param id the id of the songDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/songs/{id}")
    @Timed
    public ResponseEntity<Void> deleteSong(@PathVariable Long id) {
        log.debug("REST request to delete Song : {}", id);
        songService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
