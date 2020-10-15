package io.spiral.express.jhipster.web.rest;

import io.spiral.express.jhipster.SpiralExpressApp;
import io.spiral.express.jhipster.domain.Adresse;
import io.spiral.express.jhipster.repository.AdresseRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import io.spiral.express.jhipster.domain.enumeration.Pays;
/**
 * Integration tests for the {@link AdresseResource} REST controller.
 */
@SpringBootTest(classes = SpiralExpressApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class AdresseResourceIT {

    private static final Pays DEFAULT_PAYS = Pays.CAMEROUN;
    private static final Pays UPDATED_PAYS = Pays.RWANDA;

    private static final String DEFAULT_VILLE = "AAAAAAAAAA";
    private static final String UPDATED_VILLE = "BBBBBBBBBB";

    private static final String DEFAULT_CODE_POSTAL = "AAAAAAAAAA";
    private static final String UPDATED_CODE_POSTAL = "BBBBBBBBBB";

    private static final String DEFAULT_RUE = "AAAAAAAAAA";
    private static final String UPDATED_RUE = "BBBBBBBBBB";

    @Autowired
    private AdresseRepository adresseRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAdresseMockMvc;

    private Adresse adresse;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Adresse createEntity(EntityManager em) {
        Adresse adresse = new Adresse()
            .pays(DEFAULT_PAYS)
            .ville(DEFAULT_VILLE)
            .codePostal(DEFAULT_CODE_POSTAL)
            .rue(DEFAULT_RUE);
        return adresse;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Adresse createUpdatedEntity(EntityManager em) {
        Adresse adresse = new Adresse()
            .pays(UPDATED_PAYS)
            .ville(UPDATED_VILLE)
            .codePostal(UPDATED_CODE_POSTAL)
            .rue(UPDATED_RUE);
        return adresse;
    }

    @BeforeEach
    public void initTest() {
        adresse = createEntity(em);
    }

    @Test
    @Transactional
    public void createAdresse() throws Exception {
        int databaseSizeBeforeCreate = adresseRepository.findAll().size();
        // Create the Adresse
        restAdresseMockMvc.perform(post("/api/adresses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(adresse)))
            .andExpect(status().isCreated());

        // Validate the Adresse in the database
        List<Adresse> adresseList = adresseRepository.findAll();
        assertThat(adresseList).hasSize(databaseSizeBeforeCreate + 1);
        Adresse testAdresse = adresseList.get(adresseList.size() - 1);
        assertThat(testAdresse.getPays()).isEqualTo(DEFAULT_PAYS);
        assertThat(testAdresse.getVille()).isEqualTo(DEFAULT_VILLE);
        assertThat(testAdresse.getCodePostal()).isEqualTo(DEFAULT_CODE_POSTAL);
        assertThat(testAdresse.getRue()).isEqualTo(DEFAULT_RUE);
    }

    @Test
    @Transactional
    public void createAdresseWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = adresseRepository.findAll().size();

        // Create the Adresse with an existing ID
        adresse.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAdresseMockMvc.perform(post("/api/adresses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(adresse)))
            .andExpect(status().isBadRequest());

        // Validate the Adresse in the database
        List<Adresse> adresseList = adresseRepository.findAll();
        assertThat(adresseList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAdresses() throws Exception {
        // Initialize the database
        adresseRepository.saveAndFlush(adresse);

        // Get all the adresseList
        restAdresseMockMvc.perform(get("/api/adresses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(adresse.getId().intValue())))
            .andExpect(jsonPath("$.[*].pays").value(hasItem(DEFAULT_PAYS.toString())))
            .andExpect(jsonPath("$.[*].ville").value(hasItem(DEFAULT_VILLE)))
            .andExpect(jsonPath("$.[*].codePostal").value(hasItem(DEFAULT_CODE_POSTAL)))
            .andExpect(jsonPath("$.[*].rue").value(hasItem(DEFAULT_RUE)));
    }
    
    @Test
    @Transactional
    public void getAdresse() throws Exception {
        // Initialize the database
        adresseRepository.saveAndFlush(adresse);

        // Get the adresse
        restAdresseMockMvc.perform(get("/api/adresses/{id}", adresse.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(adresse.getId().intValue()))
            .andExpect(jsonPath("$.pays").value(DEFAULT_PAYS.toString()))
            .andExpect(jsonPath("$.ville").value(DEFAULT_VILLE))
            .andExpect(jsonPath("$.codePostal").value(DEFAULT_CODE_POSTAL))
            .andExpect(jsonPath("$.rue").value(DEFAULT_RUE));
    }
    @Test
    @Transactional
    public void getNonExistingAdresse() throws Exception {
        // Get the adresse
        restAdresseMockMvc.perform(get("/api/adresses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAdresse() throws Exception {
        // Initialize the database
        adresseRepository.saveAndFlush(adresse);

        int databaseSizeBeforeUpdate = adresseRepository.findAll().size();

        // Update the adresse
        Adresse updatedAdresse = adresseRepository.findById(adresse.getId()).get();
        // Disconnect from session so that the updates on updatedAdresse are not directly saved in db
        em.detach(updatedAdresse);
        updatedAdresse
            .pays(UPDATED_PAYS)
            .ville(UPDATED_VILLE)
            .codePostal(UPDATED_CODE_POSTAL)
            .rue(UPDATED_RUE);

        restAdresseMockMvc.perform(put("/api/adresses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedAdresse)))
            .andExpect(status().isOk());

        // Validate the Adresse in the database
        List<Adresse> adresseList = adresseRepository.findAll();
        assertThat(adresseList).hasSize(databaseSizeBeforeUpdate);
        Adresse testAdresse = adresseList.get(adresseList.size() - 1);
        assertThat(testAdresse.getPays()).isEqualTo(UPDATED_PAYS);
        assertThat(testAdresse.getVille()).isEqualTo(UPDATED_VILLE);
        assertThat(testAdresse.getCodePostal()).isEqualTo(UPDATED_CODE_POSTAL);
        assertThat(testAdresse.getRue()).isEqualTo(UPDATED_RUE);
    }

    @Test
    @Transactional
    public void updateNonExistingAdresse() throws Exception {
        int databaseSizeBeforeUpdate = adresseRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdresseMockMvc.perform(put("/api/adresses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(adresse)))
            .andExpect(status().isBadRequest());

        // Validate the Adresse in the database
        List<Adresse> adresseList = adresseRepository.findAll();
        assertThat(adresseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAdresse() throws Exception {
        // Initialize the database
        adresseRepository.saveAndFlush(adresse);

        int databaseSizeBeforeDelete = adresseRepository.findAll().size();

        // Delete the adresse
        restAdresseMockMvc.perform(delete("/api/adresses/{id}", adresse.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Adresse> adresseList = adresseRepository.findAll();
        assertThat(adresseList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
