package io.spiral.express.app.service.impl;

import io.spiral.express.app.dto.DestinataireDTO;
import io.spiral.express.app.repository.DestinataireAppRepository;
import io.spiral.express.app.service.DestinataireAppService;
import io.spiral.express.app.service.PersonneAppService;
import io.spiral.express.app.service.mapper.DestinataireMapper;
import io.spiral.express.jhipster.domain.Destinataire;
import io.spiral.express.jhipster.domain.Personne;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DestinataireAppServiceImpl implements DestinataireAppService {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final DestinataireAppRepository destinataireAppRepository;
    private final DestinataireMapper destinataireMapper;
    private final PersonneAppService personneAppService;

    public DestinataireAppServiceImpl(DestinataireAppRepository destinataireAppRepository,
                                      DestinataireMapper destinataireMapper,
                                      PersonneAppService personneAppService) {
        this.destinataireAppRepository = destinataireAppRepository;
        this.destinataireMapper = destinataireMapper;
        this.personneAppService = personneAppService;
    }

    @Override
    public DestinataireDTO sauver(DestinataireDTO destinataireDTO) {
        log.info("Créer un nouveau destinataire: {}", destinataireDTO);
        Destinataire destinataire = destinataireMapper.toEntity(destinataireDTO);
        Personne personne = personneAppService.sauver(destinataire.getPersonne());
        destinataire.setPersonne(personne);
        destinataire = destinataireAppRepository.save(destinataire);
        return destinataireMapper.toDto(destinataire);
    }

    @Override
    public DestinataireDTO modifier(DestinataireDTO destinataireDTO) {
        log.info("Modifier un destinataire: {}", destinataireDTO);
        return null;
    }

    @Override
    public DestinataireDTO findById(Long id) {
        log.info("Rechercher un destinataire par son id: {}", id);
        return null;
    }

    @Override
    public Page<DestinataireDTO> findAll() {
        log.info("Rechercher tous les destinataires");
        return null;
    }

    @Override
    public List<DestinataireDTO> findAllByClientId(Long clientId) {
        log.info("Rechercher tous les destinataires via clientId: {}", clientId);

        return destinataireAppRepository.findAllByClientId(clientId)
            .stream()
            .map(destinataireMapper::toDto)
            .collect(Collectors.toList());
    }
}
