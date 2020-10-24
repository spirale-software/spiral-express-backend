package io.spiral.express.app.service.mapper;

import io.spiral.express.app.repository.ClientAppRepository;
import io.spiral.express.app.repository.DestinataireAppRepository;
import io.spiral.express.app.service.error.ElementNonExistantException;
import io.spiral.express.jhipster.domain.Client;
import io.spiral.express.jhipster.domain.Destinataire;
import org.mapstruct.Named;
import org.springframework.stereotype.Service;

@Service
public class MapperHelperService {

    private final ClientAppRepository clientAppRepository;
    private final DestinataireAppRepository destinataireAppRepository;

    public MapperHelperService(ClientAppRepository clientAppRepository,
                               DestinataireAppRepository destinataireAppRepository) {
        this.clientAppRepository = clientAppRepository;
        this.destinataireAppRepository = destinataireAppRepository;
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

    @Named("findDestinataireById")
    public Destinataire findDestinataireById(Long destinataireId) {
        if (destinataireId == null) {
            throw new IllegalArgumentException("Le clientID ne doit pas être null.");
        }
        return destinataireAppRepository
            .findById(destinataireId)
            .orElseThrow(() -> new ElementNonExistantException("Pas de destinataire avec cet ID: " + destinataireId));
    }
}
