package io.spiral.express.app.service.impl;

import io.spiral.express.app.dto.ClientDTO;
import io.spiral.express.app.repository.ClientAppRepository;
import io.spiral.express.app.service.ClientAppService;
import io.spiral.express.app.service.mapper.ClientMapper;
import io.spiral.express.jhipster.domain.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ClientAppServiceImpl implements ClientAppService {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final ClientAppRepository clientAppRepository;
    private final ClientMapper clientMapper;

    public ClientAppServiceImpl(ClientAppRepository clientAppRepository, ClientMapper clientMapper) {
        this.clientAppRepository = clientAppRepository;
        this.clientMapper = clientMapper;
    }

    @Override
    public ClientDTO sauver(ClientDTO clientDTO) {
        log.info("Sauver un nouveau client");
        Client client = clientAppRepository.save(clientMapper.toEntity(clientDTO));
        return clientMapper.toDto(client);
    }

    @Override
    public ClientDTO modifier(ClientDTO clientDTO) {
        return null;
    }

    @Override
    public ClientDTO findById(Long id) {
        return null;
    }

    @Override
    public ClientDTO findAll() {
        return null;
    }
}
