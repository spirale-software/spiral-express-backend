package io.spiral.express.app.service;

import io.spiral.express.app.dto.ClientDTO;
import org.springframework.data.domain.Page;

public interface ClientAppService {

    ClientDTO sauver(ClientDTO clientDTO);

    ClientDTO modifier(ClientDTO clientDTO);

    ClientDTO findById(Long id);

    Page<ClientDTO> findAll();
}
