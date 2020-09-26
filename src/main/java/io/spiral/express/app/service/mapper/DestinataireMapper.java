package io.spiral.express.app.service.mapper;

import io.spiral.express.app.dto.DestinataireDTO;
import io.spiral.express.jhipster.domain.Destinataire;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {})
public interface DestinataireMapper extends EntityMapper<Destinataire, DestinataireDTO> {

    @Mapping(source = "personne.nom", target = "nom")
    @Mapping(source = "personne.prenom", target = "prenom")
    @Mapping(source = "personne.pays", target = "pays")
    @Mapping(source = "personne.adresse", target = "adresse")
    @Mapping(source = "personne.telephone", target = "telephone")
    @Mapping(source = "personne.email", target = "email")
    @Mapping(source = "client.id", target = "clientId")
    DestinataireDTO toDto(Destinataire entity);


    Destinataire toEntity(DestinataireDTO dto);
}
