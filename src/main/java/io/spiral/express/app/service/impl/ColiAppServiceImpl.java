package io.spiral.express.app.service.impl;

import io.spiral.express.app.dto.ColiDTO;
import io.spiral.express.app.repository.ColiAppRepository;
import io.spiral.express.app.service.ColiAppService;
import io.spiral.express.app.service.mapper.ColiMapper;
import io.spiral.express.jhipster.domain.Coli;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ColiAppServiceImpl implements ColiAppService {
    private Logger log = LoggerFactory.getLogger(getClass());

    private final ColiMapper coliMapper;
    private final ColiAppRepository coliAppRepository;

    public ColiAppServiceImpl(ColiMapper coliMapper, ColiAppRepository coliAppRepository) {
        this.coliMapper = coliMapper;
        this.coliAppRepository = coliAppRepository;
    }

    @Override
    public Coli create(ColiDTO dto) {
        log.info("Créer un nouveau coli: {}", dto);
        Coli coli = coliMapper.toEntity(dto);
        return coliAppRepository.save(coli);
    }
}
