package io.spiral.express.app.service.mapper;

import io.spiral.express.app.dto.EnvoiDTO;
import io.spiral.express.jhipster.domain.Envoi;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ColiMapper.class, DestinataireMapper.class, ClientMapper.class})
public interface EnvoiMapper {

    EnvoiDTO toDto(Envoi entity);

    List<EnvoiDTO> toDtos(List<Envoi> entities);

    Envoi toEntity(EnvoiDTO dto);

    List<Envoi> toEntities(List<EnvoiDTO> dtos);
}
