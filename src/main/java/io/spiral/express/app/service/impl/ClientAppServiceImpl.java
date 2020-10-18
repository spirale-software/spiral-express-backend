package io.spiral.express.app.service.impl;

import io.spiral.express.app.dto.ClientDTO;
import io.spiral.express.app.repository.ClientAppRepository;
import io.spiral.express.app.service.ClientAppService;
import io.spiral.express.app.service.PersonneAppService;
import io.spiral.express.app.service.error.ElementNonExistantException;
import io.spiral.express.app.service.mapper.ClientMapper;
import io.spiral.express.jhipster.domain.Client;
import io.spiral.express.jhipster.domain.Personne;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
@Transactional
public class ClientAppServiceImpl implements ClientAppService {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final ClientAppRepository clientAppRepository;
    private final ClientMapper clientMapper;
    private final PersonneAppService personneAppService;

    public ClientAppServiceImpl(ClientAppRepository clientAppRepository,
                                ClientMapper clientMapper,
                                PersonneAppService personneAppService) {
        this.clientAppRepository = clientAppRepository;
        this.clientMapper = clientMapper;
        this.personneAppService = personneAppService;
    }

    @Override
    public ClientDTO sauver(ClientDTO clientDTO) {
        log.info("Sauver un nouveau client");
        clientDTO.setNumero(new Random().nextLong());
        Client client = clientMapper.toEntity(clientDTO);
        Personne personne = personneAppService.sauver(client.getPersonne());
        client.setPersonne(personne);
        client = clientAppRepository.save(client);
        return clientMapper.toDto(client);
    }

    @Override
    public ClientDTO modifier(ClientDTO clientDTO) {
        log.info("Modifier un client");
        Client client = clientAppRepository.save(clientMapper.toEntity(clientDTO));
        return clientMapper.toDto(client);
    }

    @Override
    public ClientDTO findById(Long id) {
        log.info("Rechercher un client par id: {}", id);
        return clientAppRepository
            .findById(id)
            .map(clientMapper::toDto)
            .orElseThrow(() -> new ElementNonExistantException("Pas de client avec cet id: " + id));
    }

    @Override
    public Page<ClientDTO> findAll() {
        log.info("Rechercher tous les clients");
        Pageable pageable = Pageable.unpaged();
        return clientAppRepository
            .findAll(pageable)
            .map(clientMapper::toDto);
    }
}
