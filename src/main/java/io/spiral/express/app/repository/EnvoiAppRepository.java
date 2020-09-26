package io.spiral.express.app.repository;

import io.spiral.express.jhipster.domain.Envoi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnvoiAppRepository extends JpaRepository<Envoi, Long> {
}
