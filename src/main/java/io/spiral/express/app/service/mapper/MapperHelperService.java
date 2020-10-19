package io.spiral.express.app.service.mapper;

import io.spiral.express.app.repository.ClientAppRepository;
import io.spiral.express.app.service.error.ElementNonExistantException;
import io.spiral.express.jhipster.domain.Client;
import org.mapstruct.Named;
import org.springframework.stereotype.Service;

@Service
public class MapperHelperService {

    private final ClientAppRepository clientAppRepository;

    public MapperHelperService(ClientAppRepository clientAppRepository) {
        this.clientAppRepository = clientAppRepository;
    }

    @Named("findClientById")
    public Client findClientById(Long clientId) {
        if (clientId == null) {
            throw new IllegalArgumentException("Le clientID ne doit pas être null.");
        }
        return clientAppRepository
            .findById(clientId)
            .orElseThrow(() -> new ElementNonExistantException("Pas de client avec cet ID: " + clientId));
    }
}
