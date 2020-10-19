package io.spiral.express.app.repository;

import io.spiral.express.jhipster.domain.Partenaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartenaireAppRepository extends JpaRepository<Partenaire, Long> {
}
