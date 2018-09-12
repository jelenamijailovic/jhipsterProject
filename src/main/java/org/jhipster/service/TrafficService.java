package org.jhipster.service;

import org.jhipster.service.dto.TrafficDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Traffic.
 */
public interface TrafficService {

    /**
     * Save a traffic.
     *
     * @param trafficDTO the entity to save
     * @return the persisted entity
     */
    TrafficDTO save(TrafficDTO trafficDTO);

    /**
     * Get all the traffic.
     *
     * @return the list of entities
     */
    List<TrafficDTO> findAll();


    /**
     * Get the "id" traffic.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<TrafficDTO> findOne(Long id);

    /**
     * Delete the "id" traffic.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
