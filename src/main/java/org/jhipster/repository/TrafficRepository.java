package org.jhipster.repository;

import org.jhipster.domain.Traffic;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Traffic entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TrafficRepository extends JpaRepository<Traffic, Long> {

}
