package io.spiral.express.jhipster.repository;

import io.spiral.express.jhipster.domain.Partenaire;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Partenaire entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PartenaireRepository extends JpaRepository<Partenaire, Long> {
}
