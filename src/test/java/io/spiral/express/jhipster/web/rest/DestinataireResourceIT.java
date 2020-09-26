package io.spiral.express.jhipster.web.rest;

import io.spiral.express.jhipster.SpiralExpressApp;
import io.spiral.express.jhipster.domain.Destinataire;
import io.spiral.express.jhipster.repository.DestinataireRepository;

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
 * Integration tests for the {@link DestinataireResource} REST controller.
 */
@SpringBootTest(classes = SpiralExpressApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class DestinataireResourceIT {

    @Autowired
    private DestinataireRepository destinataireRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDestinataireMockMvc;

    private Destinataire destinataire;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Destinataire createEntity(EntityManager em) {
        Destinataire destinataire = new Destinataire();
        return destinataire;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Destinataire createUpdatedEntity(EntityManager em) {
        Destinataire destinataire = new Destinataire();
        return destinataire;
    }

    @BeforeEach
    public void initTest() {
        destinataire = createEntity(em);
    }

    @Test
    @Transactional
    public void createDestinataire() throws Exception {
        int databaseSizeBeforeCreate = destinataireRepository.findAll().size();
        // Create the Destinataire
        restDestinataireMockMvc.perform(post("/api/destinataires")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(destinataire)))
            .andExpect(status().isCreated());

        // Validate the Destinataire in the database
        List<Destinataire> destinataireList = destinataireRepository.findAll();
        assertThat(destinataireList).hasSize(databaseSizeBeforeCreate + 1);
        Destinataire testDestinataire = destinataireList.get(destinataireList.size() - 1);
    }

    @Test
    @Transactional
    public void createDestinataireWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = destinataireRepository.findAll().size();

        // Create the Destinataire with an existing ID
        destinataire.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDestinataireMockMvc.perform(post("/api/destinataires")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(destinataire)))
            .andExpect(status().isBadRequest());

        // Validate the Destinataire in the database
        List<Destinataire> destinataireList = destinataireRepository.findAll();
        assertThat(destinataireList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDestinataires() throws Exception {
        // Initialize the database
        destinataireRepository.saveAndFlush(destinataire);

        // Get all the destinataireList
        restDestinataireMockMvc.perform(get("/api/destinataires?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(destinataire.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getDestinataire() throws Exception {
        // Initialize the database
        destinataireRepository.saveAndFlush(destinataire);

        // Get the destinataire
        restDestinataireMockMvc.perform(get("/api/destinataires/{id}", destinataire.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(destinataire.getId().intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingDestinataire() throws Exception {
        // Get the destinataire
        restDestinataireMockMvc.perform(get("/api/destinataires/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDestinataire() throws Exception {
        // Initialize the database
        destinataireRepository.saveAndFlush(destinataire);

        int databaseSizeBeforeUpdate = destinataireRepository.findAll().size();

        // Update the destinataire
        Destinataire updatedDestinataire = destinataireRepository.findById(destinataire.getId()).get();
        // Disconnect from session so that the updates on updatedDestinataire are not directly saved in db
        em.detach(updatedDestinataire);

        restDestinataireMockMvc.perform(put("/api/destinataires")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedDestinataire)))
            .andExpect(status().isOk());

        // Validate the Destinataire in the database
        List<Destinataire> destinataireList = destinataireRepository.findAll();
        assertThat(destinataireList).hasSize(databaseSizeBeforeUpdate);
        Destinataire testDestinataire = destinataireList.get(destinataireList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingDestinataire() throws Exception {
        int databaseSizeBeforeUpdate = destinataireRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDestinataireMockMvc.perform(put("/api/destinataires")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(destinataire)))
            .andExpect(status().isBadRequest());

        // Validate the Destinataire in the database
        List<Destinataire> destinataireList = destinataireRepository.findAll();
        assertThat(destinataireList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDestinataire() throws Exception {
        // Initialize the database
        destinataireRepository.saveAndFlush(destinataire);

        int databaseSizeBeforeDelete = destinataireRepository.findAll().size();

        // Delete the destinataire
        restDestinataireMockMvc.perform(delete("/api/destinataires/{id}", destinataire.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Destinataire> destinataireList = destinataireRepository.findAll();
        assertThat(destinataireList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
