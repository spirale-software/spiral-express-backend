package io.spiral.express.app.repository;

import io.spiral.express.app.domain.Envoi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnvoiRepository extends JpaRepository<Envoi, Long> {
}
