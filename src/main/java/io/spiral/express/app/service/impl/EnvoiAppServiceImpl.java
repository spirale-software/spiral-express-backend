package io.spiral.express.app.service.impl;

import io.spiral.express.app.dto.EnvoiDTO;
import io.spiral.express.app.repository.EnvoiAppRepository;
import io.spiral.express.app.service.ColiAppService;
import io.spiral.express.app.service.EnvoiAppService;
import io.spiral.express.app.service.error.ElementNonExistantException;
import io.spiral.express.app.service.mapper.EnvoiMapper;
import io.spiral.express.jhipster.domain.Coli;
import io.spiral.express.jhipster.domain.Envoi;
import io.spiral.express.jhipster.domain.enumeration.StatutEnvoi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Random;

@Service
public class EnvoiAppServiceImpl implements EnvoiAppService {
    private Logger log = LoggerFactory.getLogger(getClass());

    private final EnvoiMapper envoiMapper;
    private final EnvoiAppRepository envoiAppRepository;
    private final ColiAppService coliAppService;

    public EnvoiAppServiceImpl(EnvoiMapper envoiMapper, EnvoiAppRepository envoiAppRepository,
                               ColiAppService coliAppService) {
        this.envoiMapper = envoiMapper;
        this.envoiAppRepository = envoiAppRepository;
        this.coliAppService = coliAppService;
    }

    @Override
    public EnvoiDTO create(EnvoiDTO dto) {
        log.info("Créer un nouvel envoi: {}", dto);

        if (dto.getExpediteur() == null || dto.getExpediteur().getPersonneId() == null) {
            System.out.println("L'expéditeur ne peut être null.");
        }
        if (dto.getDestinataire() == null || dto.getDestinataire().getPersonneId() == null) {
            System.out.println("Le destinataire ne peut être null.");
        }

        Coli coli = coliAppService.create(dto.getColi());
        Envoi envoi = envoiMapper.toEntity(dto);
        envoi.setColi(coli);
        envoi.setReference(String.valueOf(new Random().nextLong()));
        envoi.setStatut(StatutEnvoi.PRISE_EN_CHARGE);
        envoi.setDateCreation(ZonedDateTime.now());
        envoi = envoiAppRepository.save(envoi);

        return envoiMapper.toDto(envoi);
    }

    @Override
    public EnvoiDTO update(EnvoiDTO dto) {
        log.info("Modifier un envoi: {}", dto);
        return null;
    }

    @Override
    public List<EnvoiDTO> findAll() {
        log.info("Obtenir tous les Envois");

        List<Envoi> envoiList = envoiAppRepository.findAll();
        return envoiMapper.toDtos(envoiList);
    }

    @Override
    public EnvoiDTO findById(Long id) {
        log.info("Obtenir un Envoi avec pour id: {}", id);

        return envoiAppRepository
            .findById(id)
            .map(envoiMapper::toDto)
            .orElseThrow(() -> new ElementNonExistantException("Pas d'Envoi avec cet ID: " + id));
    }

    @Override
    public EnvoiDTO findByReference(String reference) {
        return envoiAppRepository
            .findByReference(reference)
            .map(envoiMapper::toDto)
            .orElseThrow(() -> new ElementNonExistantException("Pas d'Envoi avec cette référence: " + reference));
    }
}
