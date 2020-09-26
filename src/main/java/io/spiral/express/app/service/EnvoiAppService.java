package io.spiral.express.app.service;


import io.spiral.express.app.dto.EnvoiDTO;
import io.spiral.express.jhipster.domain.Envoi;

import java.util.List;

public interface EnvoiAppService {

    List<EnvoiDTO> getAll();

    EnvoiDTO findById(Long id);
}
