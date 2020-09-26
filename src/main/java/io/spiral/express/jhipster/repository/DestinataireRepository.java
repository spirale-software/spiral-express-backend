package io.spiral.express.jhipster.repository;

import io.spiral.express.jhipster.domain.Destinataire;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Destinataire entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DestinataireRepository extends JpaRepository<Destinataire, Long> {
}
