package io.spiral.express.app.repository;

import io.spiral.express.jhipster.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientAppRepository extends JpaRepository<Client, Long> {
}
