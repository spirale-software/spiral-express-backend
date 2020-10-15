package io.spiral.express.jhipster.repository;

import io.spiral.express.jhipster.domain.Adresse;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Adresse entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdresseRepository extends JpaRepository<Adresse, Long> {
}
