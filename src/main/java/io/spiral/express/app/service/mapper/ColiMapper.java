package io.spiral.express.app.service.mapper;

import io.spiral.express.app.dto.ColiDTO;
import io.spiral.express.jhipster.domain.Coli;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface ColiMapper extends EntityMapper<Coli, ColiDTO> {
}
