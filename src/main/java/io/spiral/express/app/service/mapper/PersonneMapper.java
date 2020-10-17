package io.spiral.express.app.service.mapper;

import io.spiral.express.app.dto.PersonneDTO;
import io.spiral.express.jhipster.domain.Personne;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {AdresseMapper.class})
public interface PersonneMapper extends EntityMapper<Personne, PersonneDTO> {
}
