package io.spiral.express.jhipster.repository;

import io.spiral.express.jhipster.domain.Coli;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Coli entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ColiRepository extends JpaRepository<Coli, Long> {
}
