package io.spiral.express.app.service.mapper;

import io.spiral.express.app.dto.ClientDTO;
import io.spiral.express.jhipster.domain.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {AdresseMapper.class})
public interface ClientMapper extends EntityMapper<Client, ClientDTO> {

    @Mapping(source = "personne.id", target = "personneId")
    @Mapping(source = "personne.nom", target = "nom")
    @Mapping(source = "personne.prenom", target = "prenom")
    @Mapping(source = "personne.adresse", target = "adresse")
    @Mapping(source = "personne.telephone", target = "telephone")
    @Mapping(source = "personne.email", target = "email")
    ClientDTO toDto(Client entity);

    @Mapping(target = "personne.id", source = "personneId")
    @Mapping(target = "personne.nom", source = "nom")
    @Mapping(target = "personne.prenom", source = "prenom")
    @Mapping(target = "personne.adresse", source = "adresse")
    @Mapping(target = "personne.telephone", source = "telephone")
    @Mapping(target = "personne.email", source = "email")
    Client toEntity(ClientDTO dto);
}
