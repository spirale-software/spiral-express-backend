package io.spiral.express.app.service.mapper;

import io.spiral.express.app.dto.DestinataireDTO;
import io.spiral.express.app.service.ClientAppService;
import io.spiral.express.jhipster.domain.Destinataire;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {MapperHelperService.class})
public interface DestinataireMapper extends EntityMapper<Destinataire, DestinataireDTO> {

    @Mapping(source = "personne.nom", target = "nom")
    @Mapping(source = "personne.prenom", target = "prenom")
    @Mapping(source = "personne.adresse", target = "adresse")
    @Mapping(source = "personne.telephone", target = "telephone")
    @Mapping(source = "personne.email", target = "email")
    @Mapping(source = "client.personne.id", target = "client.id")
    @Mapping(source = "client.personne.nom", target = "client.nom")
    @Mapping(source = "client.personne.prenom", target = "client.prenom")
    DestinataireDTO toDto(Destinataire entity);

    @Mapping(source = "nom", target = "personne.nom")
    @Mapping(source = "prenom", target = "personne.prenom")
    @Mapping(source = "adresse", target = "personne.adresse")
    @Mapping(source = "telephone", target = "personne.telephone")
    @Mapping(source = "email", target = "personne.email")
    @Mapping(source = "client.id", target = "client", qualifiedByName = "findClientById")
    Destinataire toEntity(DestinataireDTO dto);
}
