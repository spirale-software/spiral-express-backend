package io.spiral.express.app.service.impl;

import io.spiral.express.app.dto.EnvoiDTO;
import io.spiral.express.app.repository.EnvoiAppRepository;
import io.spiral.express.app.service.EnvoiAppService;
import io.spiral.express.app.service.error.ElementNonExistantException;
import io.spiral.express.app.service.mapper.EnvoiMapper;
import io.spiral.express.jhipster.domain.Envoi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnvoiAppServiceImpl implements EnvoiAppService {
    private Logger log = LoggerFactory.getLogger(getClass());

    private final EnvoiMapper envoiMapper;
    private final EnvoiAppRepository envoiAppRepository;

    public EnvoiAppServiceImpl(EnvoiMapper envoiMapper, EnvoiAppRepository envoiAppRepository) {
        this.envoiMapper = envoiMapper;
        this.envoiAppRepository = envoiAppRepository;
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
}
