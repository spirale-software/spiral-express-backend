package io.spiral.express.app.service.mapper;

import io.spiral.express.app.dto.ClientDTO;
import io.spiral.express.app.dto.PartenaireDTO;
import io.spiral.express.jhipster.domain.Client;
import io.spiral.express.jhipster.domain.Partenaire;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {})

public interface PartenaireMapper extends EntityMapper<Partenaire, PartenaireDTO> {
    //    @Mapping(source = "personne.id", target = "personneDTO.Id")
    @Mapping(source = "personne.nom", target = "nom")
    @Mapping(source = "personne.prenom", target = "prenom")
    @Mapping(source = "personne.adresse", target = "adresse")
    @Mapping(source = "personne.telephone", target = "telephone")
    @Mapping(source = "personne.email", target = "email")
    ClientDTO toDto(Client entity);

    //    @Mapping(source = "personneId", target = "personne.id")
    @Mapping(source = "nom", target = "personne.nom")
    @Mapping(source = "prenom", target = "personne.prenom")
    @Mapping(source = "adresse", target = "personne.adresse")
    @Mapping(source = "telephone", target = "personne.telephone")
    @Mapping(source = "email", target = "personne.email")
    Client toEntity(ClientDTO dto);
}
