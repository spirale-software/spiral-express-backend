package io.spiral.express.app.service.mapper;

import io.spiral.express.app.dto.PersonneDTO;
import io.spiral.express.jhipster.domain.Personne;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface PersonneMapper extends EntityMapper<Personne, PersonneDTO> {
}
