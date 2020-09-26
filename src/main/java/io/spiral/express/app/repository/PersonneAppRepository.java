package io.spiral.express.app.repository;

import io.spiral.express.jhipster.domain.Personne;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonneAppRepository extends JpaRepository<Personne, Long> {
}
