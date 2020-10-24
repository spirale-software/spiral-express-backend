package io.spiral.express.app.service;


import io.spiral.express.app.dto.EnvoiDTO;

import java.util.List;

public interface EnvoiAppService {

    EnvoiDTO create(EnvoiDTO dto);

    EnvoiDTO update(EnvoiDTO dto);

    List<EnvoiDTO> findAll();

    EnvoiDTO findById(Long id);

    EnvoiDTO findByReference(String reference);

}
