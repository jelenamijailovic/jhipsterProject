package org.jhipster.web.rest;

import com.codahale.metrics.annotation.Timed;
import org.jhipster.service.TrafficService;
import org.jhipster.web.rest.errors.BadRequestAlertException;
import org.jhipster.web.rest.util.HeaderUtil;
import org.jhipster.service.dto.TrafficDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Traffic.
 */
@RestController
@RequestMapping("/api")
public class TrafficResource {

    private final Logger log = LoggerFactory.getLogger(TrafficResource.class);

    private static final String ENTITY_NAME = "traffic";

    private final TrafficService trafficService;

    public TrafficResource(TrafficService trafficService) {
        this.trafficService = trafficService;
    }

    /**
     * POST  /traffic : Create a new traffic.
     *
     * @param trafficDTO the trafficDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new trafficDTO, or with status 400 (Bad Request) if the traffic has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/traffic")
    @Timed
    public ResponseEntity<TrafficDTO> createTraffic(@RequestBody TrafficDTO trafficDTO) throws URISyntaxException {
        log.debug("REST request to save Traffic : {}", trafficDTO);
        if (trafficDTO.getId() != null) {
            throw new BadRequestAlertException("A new traffic cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TrafficDTO result = trafficService.save(trafficDTO);
        return ResponseEntity.created(new URI("/api/traffic/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /traffic : Updates an existing traffic.
     *
     * @param trafficDTO the trafficDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated trafficDTO,
     * or with status 400 (Bad Request) if the trafficDTO is not valid,
     * or with status 500 (Internal Server Error) if the trafficDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/traffic")
    @Timed
    public ResponseEntity<TrafficDTO> updateTraffic(@RequestBody TrafficDTO trafficDTO) throws URISyntaxException {
        log.debug("REST request to update Traffic : {}", trafficDTO);
        if (trafficDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TrafficDTO result = trafficService.save(trafficDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, trafficDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /traffic : get all the traffic.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of traffic in body
     */
    @GetMapping("/traffic")
    @Timed
    public List<TrafficDTO> getAllTraffic() {
        log.debug("REST request to get all Traffic");
        return trafficService.findAll();
    }

    /**
     * GET  /traffic/:id : get the "id" traffic.
     *
     * @param id the id of the trafficDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the trafficDTO, or with status 404 (Not Found)
     */
    @GetMapping("/traffic/{id}")
    @Timed
    public ResponseEntity<TrafficDTO> getTraffic(@PathVariable Long id) {
        log.debug("REST request to get Traffic : {}", id);
        Optional<TrafficDTO> trafficDTO = trafficService.findOne(id);
        return ResponseUtil.wrapOrNotFound(trafficDTO);
    }

    /**
     * DELETE  /traffic/:id : delete the "id" traffic.
     *
     * @param id the id of the trafficDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/traffic/{id}")
    @Timed
    public ResponseEntity<Void> deleteTraffic(@PathVariable Long id) {
        log.debug("REST request to delete Traffic : {}", id);
        trafficService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
