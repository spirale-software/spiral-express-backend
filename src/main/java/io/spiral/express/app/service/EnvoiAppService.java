package io.spiral.express.app.service;


import io.spiral.express.app.dto.EnvoiDTO;

import java.util.List;

public interface EnvoiAppService {

    List<EnvoiDTO> findAll();

    EnvoiDTO findById(Long id);
}
