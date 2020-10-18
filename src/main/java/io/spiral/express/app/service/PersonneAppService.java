package io.spiral.express.app.service;

import io.spiral.express.app.dto.PersonneDTO;
import io.spiral.express.jhipster.domain.Personne;

public interface PersonneAppService {

    Personne sauver(PersonneDTO personneDTO);

    Personne sauver(Personne personne);
}
