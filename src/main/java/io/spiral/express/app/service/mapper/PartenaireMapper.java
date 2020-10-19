package io.spiral.express.app.service.mapper;

import io.spiral.express.app.dto.PartenaireDTO;
import io.spiral.express.jhipster.domain.Partenaire;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})

public interface PartenaireMapper extends EntityMapper<Partenaire, PartenaireDTO> {
}
