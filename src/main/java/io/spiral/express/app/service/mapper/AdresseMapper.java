package io.spiral.express.app.service.mapper;

import io.spiral.express.app.dto.AdresseDTO;
import io.spiral.express.jhipster.domain.Adresse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface AdresseMapper extends EntityMapper<Adresse, AdresseDTO> {
}
