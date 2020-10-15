package io.spiral.express.jhipster.web.rest;

import io.spiral.express.jhipster.SpiralExpressApp;
import io.spiral.express.jhipster.domain.Partenaire;
import io.spiral.express.jhipster.repository.PartenaireRepository;

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
 * Integration tests for the {@link PartenaireResource} REST controller.
 */
@SpringBootTest(classes = SpiralExpressApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PartenaireResourceIT {

    private static final Long DEFAULT_NUMERO = 1L;
    private static final Long UPDATED_NUMERO = 2L;

    @Autowired
    private PartenaireRepository partenaireRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPartenaireMockMvc;

    private Partenaire partenaire;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Partenaire createEntity(EntityManager em) {
        Partenaire partenaire = new Partenaire()
            .numero(DEFAULT_NUMERO);
        return partenaire;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Partenaire createUpdatedEntity(EntityManager em) {
        Partenaire partenaire = new Partenaire()
            .numero(UPDATED_NUMERO);
        return partenaire;
    }

    @BeforeEach
    public void initTest() {
        partenaire = createEntity(em);
    }

    @Test
    @Transactional
    public void createPartenaire() throws Exception {
        int databaseSizeBeforeCreate = partenaireRepository.findAll().size();
        // Create the Partenaire
        restPartenaireMockMvc.perform(post("/api/partenaires")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(partenaire)))
            .andExpect(status().isCreated());

        // Validate the Partenaire in the database
        List<Partenaire> partenaireList = partenaireRepository.findAll();
        assertThat(partenaireList).hasSize(databaseSizeBeforeCreate + 1);
        Partenaire testPartenaire = partenaireList.get(partenaireList.size() - 1);
        assertThat(testPartenaire.getNumero()).isEqualTo(DEFAULT_NUMERO);
    }

    @Test
    @Transactional
    public void createPartenaireWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = partenaireRepository.findAll().size();

        // Create the Partenaire with an existing ID
        partenaire.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPartenaireMockMvc.perform(post("/api/partenaires")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(partenaire)))
            .andExpect(status().isBadRequest());

        // Validate the Partenaire in the database
        List<Partenaire> partenaireList = partenaireRepository.findAll();
        assertThat(partenaireList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllPartenaires() throws Exception {
        // Initialize the database
        partenaireRepository.saveAndFlush(partenaire);

        // Get all the partenaireList
        restPartenaireMockMvc.perform(get("/api/partenaires?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(partenaire.getId().intValue())))
            .andExpect(jsonPath("$.[*].numero").value(hasItem(DEFAULT_NUMERO.intValue())));
    }
    
    @Test
    @Transactional
    public void getPartenaire() throws Exception {
        // Initialize the database
        partenaireRepository.saveAndFlush(partenaire);

        // Get the partenaire
        restPartenaireMockMvc.perform(get("/api/partenaires/{id}", partenaire.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(partenaire.getId().intValue()))
            .andExpect(jsonPath("$.numero").value(DEFAULT_NUMERO.intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingPartenaire() throws Exception {
        // Get the partenaire
        restPartenaireMockMvc.perform(get("/api/partenaires/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePartenaire() throws Exception {
        // Initialize the database
        partenaireRepository.saveAndFlush(partenaire);

        int databaseSizeBeforeUpdate = partenaireRepository.findAll().size();

        // Update the partenaire
        Partenaire updatedPartenaire = partenaireRepository.findById(partenaire.getId()).get();
        // Disconnect from session so that the updates on updatedPartenaire are not directly saved in db
        em.detach(updatedPartenaire);
        updatedPartenaire
            .numero(UPDATED_NUMERO);

        restPartenaireMockMvc.perform(put("/api/partenaires")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedPartenaire)))
            .andExpect(status().isOk());

        // Validate the Partenaire in the database
        List<Partenaire> partenaireList = partenaireRepository.findAll();
        assertThat(partenaireList).hasSize(databaseSizeBeforeUpdate);
        Partenaire testPartenaire = partenaireList.get(partenaireList.size() - 1);
        assertThat(testPartenaire.getNumero()).isEqualTo(UPDATED_NUMERO);
    }

    @Test
    @Transactional
    public void updateNonExistingPartenaire() throws Exception {
        int databaseSizeBeforeUpdate = partenaireRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPartenaireMockMvc.perform(put("/api/partenaires")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(partenaire)))
            .andExpect(status().isBadRequest());

        // Validate the Partenaire in the database
        List<Partenaire> partenaireList = partenaireRepository.findAll();
        assertThat(partenaireList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePartenaire() throws Exception {
        // Initialize the database
        partenaireRepository.saveAndFlush(partenaire);

        int databaseSizeBeforeDelete = partenaireRepository.findAll().size();

        // Delete the partenaire
        restPartenaireMockMvc.perform(delete("/api/partenaires/{id}", partenaire.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Partenaire> partenaireList = partenaireRepository.findAll();
        assertThat(partenaireList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
