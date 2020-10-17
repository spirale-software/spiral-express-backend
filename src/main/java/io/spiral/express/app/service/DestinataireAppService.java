package io.spiral.express.app.service;

import io.spiral.express.app.dto.DestinataireDTO;
import org.springframework.data.domain.Page;

public interface DestinataireAppService {

    DestinataireDTO sauver(DestinataireDTO clientDTO);

    DestinataireDTO modifier(DestinataireDTO clientDTO);

    DestinataireDTO findById(Long id);

    Page<DestinataireDTO> findAll();
}
