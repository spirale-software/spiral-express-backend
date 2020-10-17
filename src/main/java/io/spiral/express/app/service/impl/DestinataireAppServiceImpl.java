package io.spiral.express.app.service.impl;

import io.spiral.express.app.dto.DestinataireDTO;
import io.spiral.express.app.service.DestinataireAppService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class DestinataireAppServiceImpl implements DestinataireAppService {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public DestinataireDTO sauver(DestinataireDTO destinataireDTO) {
        log.info("Créer un nouveau destinataire: {}", destinataireDTO);
        return null;
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
}
