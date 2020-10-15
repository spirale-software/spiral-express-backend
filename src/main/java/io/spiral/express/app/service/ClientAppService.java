package io.spiral.express.app.service;

import io.spiral.express.app.dto.ClientDTO;

public interface ClientAppService {

    ClientDTO sauver(ClientDTO clientDTO);

    ClientDTO modifier(ClientDTO clientDTO);

    ClientDTO findById(Long id);

    ClientDTO findAll();
}
