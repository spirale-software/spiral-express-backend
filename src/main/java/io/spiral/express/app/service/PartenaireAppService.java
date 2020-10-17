package io.spiral.express.app.service;

import io.spiral.express.app.dto.PartenaireDTO;
import org.springframework.data.domain.Page;

public interface PartenaireAppService {

    PartenaireDTO sauver(PartenaireDTO clientDTO);

    PartenaireDTO modifier(PartenaireDTO clientDTO);

    PartenaireDTO findById(Long id);

    Page<PartenaireDTO> findAll();
}
