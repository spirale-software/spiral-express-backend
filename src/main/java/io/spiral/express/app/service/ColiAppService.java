package io.spiral.express.app.service;

import io.spiral.express.app.dto.ColiDTO;
import io.spiral.express.jhipster.domain.Coli;

public interface ColiAppService {

    Coli create(ColiDTO dto);
}
