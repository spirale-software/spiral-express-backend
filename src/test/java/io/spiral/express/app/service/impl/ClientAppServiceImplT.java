package io.spiral.express.app.service.impl;

import io.spiral.express.app.dto.AdresseDTO;
import io.spiral.express.app.dto.ClientDTO;
import io.spiral.express.app.service.ClientAppService;
import io.spiral.express.jhipster.SpiralExpressApp;
import io.spiral.express.jhipster.domain.enumeration.Pays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = SpiralExpressApp.class)
@Transactional
class ClientAppServiceImplT {

    @Autowired
    private ClientAppService clientAppService;

    @Test
    void sauver() {
        // GIVEN
        ClientDTO dto = new ClientDTO();
        dto.setNom("TEST");
        dto.setPrenom("TEST");
        dto.setEmail("test@gmail.com");
        dto.setTelephone("12396985");
        AdresseDTO adresseDTO = new AdresseDTO();
        adresseDTO.setCodePostal("5000");
        adresseDTO.setPays(Pays.CAMEROUN);
        adresseDTO.setRue("Ma rue");
        dto.setAdresse(adresseDTO);

        // WHEN
        ClientDTO resultat = clientAppService.sauver(dto);

        // THEN
        assertNotNull(resultat.getId());
        assertNotNull(resultat.getNumero());
        assertEquals(dto.getNom(), resultat.getNom());
        assertEquals(dto.getPrenom(), resultat.getPrenom());
        assertEquals(dto.getEmail(), resultat.getEmail());
        assertEquals(dto.getTelephone(), resultat.getTelephone());
        assertEquals(dto.getAdresse().getPays(), resultat.getAdresse().getPays());
        assertEquals(dto.getAdresse().getCodePostal(), resultat.getAdresse().getCodePostal());
        assertEquals(dto.getAdresse().getVille(), resultat.getAdresse().getVille());
    }

    @Test
    void modifier() {
    }
}
