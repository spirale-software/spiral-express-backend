package io.spiral.express.app.repository;

import io.spiral.express.jhipster.domain.Destinataire;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DestinataireAppRepository extends JpaRepository<Destinataire, Long> {
    List<Destinataire> findAllByClientId(Long clientId);
}
