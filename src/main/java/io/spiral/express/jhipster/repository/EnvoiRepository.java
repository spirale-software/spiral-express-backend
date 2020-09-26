package io.spiral.express.jhipster.repository;

import io.spiral.express.jhipster.domain.Envoi;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Envoi entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EnvoiRepository extends JpaRepository<Envoi, Long> {
}
