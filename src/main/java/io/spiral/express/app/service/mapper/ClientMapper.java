package io.spiral.express.app.service.mapper;

import io.spiral.express.app.dto.ClientDTO;
import io.spiral.express.jhipster.domain.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {})
public interface ClientMapper extends EntityMapper<Client, ClientDTO> {

    @Mapping(source = "personne.nom", target = "nom")
    @Mapping(source = "personne.prenom", target = "prenom")
    @Mapping(source = "personne.pays", target = "pays")
    @Mapping(source = "personne.adresse", target = "adresse")
    @Mapping(source = "personne.telephone", target = "telephone")
    @Mapping(source = "personne.email", target = "email")
    ClientDTO toDto(Client entity);

    Client toEntity(ClientDTO dto);
}
