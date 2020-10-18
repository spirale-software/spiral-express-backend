package io.spiral.express.app.service.impl;

import io.spiral.express.app.dto.PersonneDTO;
import io.spiral.express.app.repository.AdresseAppRepository;
import io.spiral.express.app.repository.PersonneAppRepository;
import io.spiral.express.app.service.PersonneAppService;
import io.spiral.express.app.service.mapper.PersonneMapper;
import io.spiral.express.jhipster.domain.Adresse;
import io.spiral.express.jhipster.domain.Personne;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PersonneAppServiceImpl implements PersonneAppService {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final AdresseAppRepository adresseAppRepository;
    private final PersonneAppRepository personneAppRepository;
    private final PersonneMapper personneMapper;

    public PersonneAppServiceImpl(AdresseAppRepository adresseAppRepository,
                                  PersonneAppRepository personneAppRepository,
                                  PersonneMapper personneMapper) {
        this.adresseAppRepository = adresseAppRepository;
        this.personneAppRepository = personneAppRepository;
        this.personneMapper = personneMapper;
    }

    @Override
    public Personne sauver(PersonneDTO personneDTO) {
        Personne personne = personneMapper.toEntity(personneDTO);
        return sauver(personne);
    }

    @Override
    public Personne sauver(Personne personne) {
        Adresse adresse = adresseAppRepository.save(personne.getAdresse());
        personne.setAdresse(adresse);
        return personneAppRepository.save(personne);
    }
}
