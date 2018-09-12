package org.jhipster.web.rest;

import com.codahale.metrics.annotation.Timed;
import org.jhipster.service.PriceService;
import org.jhipster.web.rest.errors.BadRequestAlertException;
import org.jhipster.web.rest.util.HeaderUtil;
import org.jhipster.service.dto.PriceDTO;
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
 * REST controller for managing Price.
 */
@RestController
@RequestMapping("/api")
public class PriceResource {

    private final Logger log = LoggerFactory.getLogger(PriceResource.class);

    private static final String ENTITY_NAME = "price";

    private final PriceService priceService;

    public PriceResource(PriceService priceService) {
        this.priceService = priceService;
    }

    /**
     * POST  /prices : Create a new price.
     *
     * @param priceDTO the priceDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new priceDTO, or with status 400 (Bad Request) if the price has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/prices")
    @Timed
    public ResponseEntity<PriceDTO> createPrice(@RequestBody PriceDTO priceDTO) throws URISyntaxException {
        log.debug("REST request to save Price : {}", priceDTO);
        if (priceDTO.getId() != null) {
            throw new BadRequestAlertException("A new price cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PriceDTO result = priceService.save(priceDTO);
        return ResponseEntity.created(new URI("/api/prices/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /prices : Updates an existing price.
     *
     * @param priceDTO the priceDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated priceDTO,
     * or with status 400 (Bad Request) if the priceDTO is not valid,
     * or with status 500 (Internal Server Error) if the priceDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/prices")
    @Timed
    public ResponseEntity<PriceDTO> updatePrice(@RequestBody PriceDTO priceDTO) throws URISyntaxException {
        log.debug("REST request to update Price : {}", priceDTO);
        if (priceDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PriceDTO result = priceService.save(priceDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, priceDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /prices : get all the prices.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of prices in body
     */
    @GetMapping("/prices")
    @Timed
    public List<PriceDTO> getAllPrices() {
        log.debug("REST request to get all Prices");
        return priceService.findAll();
    }

    /**
     * GET  /prices/:id : get the "id" price.
     *
     * @param id the id of the priceDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the priceDTO, or with status 404 (Not Found)
     */
    @GetMapping("/prices/{id}")
    @Timed
    public ResponseEntity<PriceDTO> getPrice(@PathVariable Long id) {
        log.debug("REST request to get Price : {}", id);
        Optional<PriceDTO> priceDTO = priceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(priceDTO);
    }

    /**
     * DELETE  /prices/:id : delete the "id" price.
     *
     * @param id the id of the priceDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/prices/{id}")
    @Timed
    public ResponseEntity<Void> deletePrice(@PathVariable Long id) {
        log.debug("REST request to delete Price : {}", id);
        priceService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
