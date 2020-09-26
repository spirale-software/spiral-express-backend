package io.spiral.express.app.repository;

import io.spiral.express.jhipster.domain.Coli;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColiAppRepository extends JpaRepository<Coli, Long> {
}
