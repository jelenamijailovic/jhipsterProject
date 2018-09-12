package org.jhipster.service.impl;

import org.jhipster.service.TrafficService;
import org.jhipster.domain.Traffic;
import org.jhipster.repository.TrafficRepository;
import org.jhipster.service.dto.TrafficDTO;
import org.jhipster.service.mapper.TrafficMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Service Implementation for managing Traffic.
 */
@Service
@Transactional
public class TrafficServiceImpl implements TrafficService {

    private final Logger log = LoggerFactory.getLogger(TrafficServiceImpl.class);

    private final TrafficRepository trafficRepository;

    private final TrafficMapper trafficMapper;

    public TrafficServiceImpl(TrafficRepository trafficRepository, TrafficMapper trafficMapper) {
        this.trafficRepository = trafficRepository;
        this.trafficMapper = trafficMapper;
    }

    /**
     * Save a traffic.
     *
     * @param trafficDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public TrafficDTO save(TrafficDTO trafficDTO) {
        log.debug("Request to save Traffic : {}", trafficDTO);
        Traffic traffic = trafficMapper.toEntity(trafficDTO);
        traffic = trafficRepository.save(traffic);
        return trafficMapper.toDto(traffic);
    }

    /**
     * Get all the traffic.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<TrafficDTO> findAll() {
        log.debug("Request to get all Traffic");
        return trafficRepository.findAll().stream()
            .map(trafficMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one traffic by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TrafficDTO> findOne(Long id) {
        log.debug("Request to get Traffic : {}", id);
        return trafficRepository.findById(id)
            .map(trafficMapper::toDto);
    }

    /**
     * Delete the traffic by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Traffic : {}", id);
        trafficRepository.deleteById(id);
    }
}
