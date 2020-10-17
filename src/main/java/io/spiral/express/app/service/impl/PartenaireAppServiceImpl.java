package io.spiral.express.app.service.impl;

import io.spiral.express.app.dto.PartenaireDTO;
import io.spiral.express.app.service.PartenaireAppService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class PartenaireAppServiceImpl implements PartenaireAppService {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public PartenaireDTO sauver(PartenaireDTO partenaireDTO) {
        log.info("Créer un nouveau partenaire: {}", partenaireDTO);
        return null;
    }

    @Override
    public PartenaireDTO modifier(PartenaireDTO partenaireDTO) {
        log.info("Modifier un partenaire: {}", partenaireDTO);
        return null;
    }

    @Override
    public PartenaireDTO findById(Long id) {
        log.info("Rechercher un partenaire via l'id: {}", id);
        return null;
    }

    @Override
    public Page<PartenaireDTO> findAll() {
        log.info("Rechercher tous les partenaire");
        return null;
    }
}
