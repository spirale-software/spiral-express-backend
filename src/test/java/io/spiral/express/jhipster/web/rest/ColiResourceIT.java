package io.spiral.express.jhipster.web.rest;

import io.spiral.express.jhipster.SpiralExpressApp;
import io.spiral.express.jhipster.domain.Coli;
import io.spiral.express.jhipster.repository.ColiRepository;

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

/**
 * Integration tests for the {@link ColiResource} REST controller.
 */
@SpringBootTest(classes = SpiralExpressApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ColiResourceIT {

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Double DEFAULT_LONGUEUR = 1D;
    private static final Double UPDATED_LONGUEUR = 2D;

    private static final Double DEFAULT_LARGEUR = 1D;
    private static final Double UPDATED_LARGEUR = 2D;

    private static final Double DEFAULT_HAUTEUR = 1D;
    private static final Double UPDATED_HAUTEUR = 2D;

    private static final Double DEFAULT_POIDS = 1D;
    private static final Double UPDATED_POIDS = 2D;

    @Autowired
    private ColiRepository coliRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restColiMockMvc;

    private Coli coli;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Coli createEntity(EntityManager em) {
        Coli coli = new Coli()
            .description(DEFAULT_DESCRIPTION)
            .longueur(DEFAULT_LONGUEUR)
            .largeur(DEFAULT_LARGEUR)
            .hauteur(DEFAULT_HAUTEUR)
            .poids(DEFAULT_POIDS);
        return coli;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Coli createUpdatedEntity(EntityManager em) {
        Coli coli = new Coli()
            .description(UPDATED_DESCRIPTION)
            .longueur(UPDATED_LONGUEUR)
            .largeur(UPDATED_LARGEUR)
            .hauteur(UPDATED_HAUTEUR)
            .poids(UPDATED_POIDS);
        return coli;
    }

    @BeforeEach
    public void initTest() {
        coli = createEntity(em);
    }

    @Test
    @Transactional
    public void createColi() throws Exception {
        int databaseSizeBeforeCreate = coliRepository.findAll().size();
        // Create the Coli
        restColiMockMvc.perform(post("/api/colis")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(coli)))
            .andExpect(status().isCreated());

        // Validate the Coli in the database
        List<Coli> coliList = coliRepository.findAll();
        assertThat(coliList).hasSize(databaseSizeBeforeCreate + 1);
        Coli testColi = coliList.get(coliList.size() - 1);
        assertThat(testColi.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testColi.getLongueur()).isEqualTo(DEFAULT_LONGUEUR);
        assertThat(testColi.getLargeur()).isEqualTo(DEFAULT_LARGEUR);
        assertThat(testColi.getHauteur()).isEqualTo(DEFAULT_HAUTEUR);
        assertThat(testColi.getPoids()).isEqualTo(DEFAULT_POIDS);
    }

    @Test
    @Transactional
    public void createColiWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = coliRepository.findAll().size();

        // Create the Coli with an existing ID
        coli.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restColiMockMvc.perform(post("/api/colis")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(coli)))
            .andExpect(status().isBadRequest());

        // Validate the Coli in the database
        List<Coli> coliList = coliRepository.findAll();
        assertThat(coliList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllColis() throws Exception {
        // Initialize the database
        coliRepository.saveAndFlush(coli);

        // Get all the coliList
        restColiMockMvc.perform(get("/api/colis?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(coli.getId().intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].longueur").value(hasItem(DEFAULT_LONGUEUR.doubleValue())))
            .andExpect(jsonPath("$.[*].largeur").value(hasItem(DEFAULT_LARGEUR.doubleValue())))
            .andExpect(jsonPath("$.[*].hauteur").value(hasItem(DEFAULT_HAUTEUR.doubleValue())))
            .andExpect(jsonPath("$.[*].poids").value(hasItem(DEFAULT_POIDS.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getColi() throws Exception {
        // Initialize the database
        coliRepository.saveAndFlush(coli);

        // Get the coli
        restColiMockMvc.perform(get("/api/colis/{id}", coli.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(coli.getId().intValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.longueur").value(DEFAULT_LONGUEUR.doubleValue()))
            .andExpect(jsonPath("$.largeur").value(DEFAULT_LARGEUR.doubleValue()))
            .andExpect(jsonPath("$.hauteur").value(DEFAULT_HAUTEUR.doubleValue()))
            .andExpect(jsonPath("$.poids").value(DEFAULT_POIDS.doubleValue()));
    }
    @Test
    @Transactional
    public void getNonExistingColi() throws Exception {
        // Get the coli
        restColiMockMvc.perform(get("/api/colis/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateColi() throws Exception {
        // Initialize the database
        coliRepository.saveAndFlush(coli);

        int databaseSizeBeforeUpdate = coliRepository.findAll().size();

        // Update the coli
        Coli updatedColi = coliRepository.findById(coli.getId()).get();
        // Disconnect from session so that the updates on updatedColi are not directly saved in db
        em.detach(updatedColi);
        updatedColi
            .description(UPDATED_DESCRIPTION)
            .longueur(UPDATED_LONGUEUR)
            .largeur(UPDATED_LARGEUR)
            .hauteur(UPDATED_HAUTEUR)
            .poids(UPDATED_POIDS);

        restColiMockMvc.perform(put("/api/colis")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedColi)))
            .andExpect(status().isOk());

        // Validate the Coli in the database
        List<Coli> coliList = coliRepository.findAll();
        assertThat(coliList).hasSize(databaseSizeBeforeUpdate);
        Coli testColi = coliList.get(coliList.size() - 1);
        assertThat(testColi.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testColi.getLongueur()).isEqualTo(UPDATED_LONGUEUR);
        assertThat(testColi.getLargeur()).isEqualTo(UPDATED_LARGEUR);
        assertThat(testColi.getHauteur()).isEqualTo(UPDATED_HAUTEUR);
        assertThat(testColi.getPoids()).isEqualTo(UPDATED_POIDS);
    }

    @Test
    @Transactional
    public void updateNonExistingColi() throws Exception {
        int databaseSizeBeforeUpdate = coliRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restColiMockMvc.perform(put("/api/colis")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(coli)))
            .andExpect(status().isBadRequest());

        // Validate the Coli in the database
        List<Coli> coliList = coliRepository.findAll();
        assertThat(coliList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteColi() throws Exception {
        // Initialize the database
        coliRepository.saveAndFlush(coli);

        int databaseSizeBeforeDelete = coliRepository.findAll().size();

        // Delete the coli
        restColiMockMvc.perform(delete("/api/colis/{id}", coli.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Coli> coliList = coliRepository.findAll();
        assertThat(coliList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
