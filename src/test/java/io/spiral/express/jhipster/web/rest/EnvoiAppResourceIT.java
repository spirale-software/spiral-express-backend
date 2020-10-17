package io.spiral.express.jhipster.web.rest;

import io.spiral.express.jhipster.SpiralExpressApp;
import io.spiral.express.jhipster.domain.Envoi;
import io.spiral.express.jhipster.repository.EnvoiRepository;

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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import io.spiral.express.jhipster.domain.enumeration.StatutEnvoi;
/**
 * Integration tests for the {@link EnvoiResource} REST controller.
 */
@SpringBootTest(classes = SpiralExpressApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class EnvoiAppResourceIT {

    private static final LocalDate DEFAULT_DATE_CREATION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_CREATION = LocalDate.now(ZoneId.systemDefault());

    private static final StatutEnvoi DEFAULT_STATUT = StatutEnvoi.PRISE_EN_CHARGE;
    private static final StatutEnvoi UPDATED_STATUT = StatutEnvoi.EN_ATTENTE;

    private static final String DEFAULT_REFERENCE = "AAAAAAAAAA";
    private static final String UPDATED_REFERENCE = "BBBBBBBBBB";

    private static final String DEFAULT_RAPPORT_QUAI = "AAAAAAAAAA";
    private static final String UPDATED_RAPPORT_QUAI = "BBBBBBBBBB";

    private static final String DEFAULT_RAPPORT_LIVRAISAON = "AAAAAAAAAA";
    private static final String UPDATED_RAPPORT_LIVRAISAON = "BBBBBBBBBB";

    private static final Double DEFAULT_MONTANT = 1D;
    private static final Double UPDATED_MONTANT = 2D;

    @Autowired
    private EnvoiRepository envoiRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEnvoiMockMvc;

    private Envoi envoi;

//    /**
//     * Create an entity for this test.
//     *
//     * This is a static method, as tests for other entities might also need it,
//     * if they test an entity which requires the current entity.
//     */
//    public static Envoi createEntity(EntityManager em) {
//        Envoi envoi = new Envoi()
//            .dateCreation(DEFAULT_DATE_CREATION)
//            .statut(DEFAULT_STATUT)
//            .reference(DEFAULT_REFERENCE)
//            .rapportQuai(DEFAULT_RAPPORT_QUAI)
//            .rapportLivraisaon(DEFAULT_RAPPORT_LIVRAISAON)
//            .montant(DEFAULT_MONTANT);
//        return envoi;
//    }
//    /**
//     * Create an updated entity for this test.
//     *
//     * This is a static method, as tests for other entities might also need it,
//     * if they test an entity which requires the current entity.
//     */
//    public static Envoi createUpdatedEntity(EntityManager em) {
//        Envoi envoi = new Envoi()
//            .dateCreation(UPDATED_DATE_CREATION)
//            .statut(UPDATED_STATUT)
//            .reference(UPDATED_REFERENCE)
//            .rapportQuai(UPDATED_RAPPORT_QUAI)
//            .rapportLivraisaon(UPDATED_RAPPORT_LIVRAISAON)
//            .montant(UPDATED_MONTANT);
//        return envoi;
//    }
//
//    @BeforeEach
//    public void initTest() {
//        envoi = createEntity(em);
//    }
//
//    @Test
//    @Transactional
//    public void createEnvoi() throws Exception {
//        int databaseSizeBeforeCreate = envoiRepository.findAll().size();
//        // Create the Envoi
//        restEnvoiMockMvc.perform(post("/api/envois")
//            .contentType(MediaType.APPLICATION_JSON)
//            .content(TestUtil.convertObjectToJsonBytes(envoi)))
//            .andExpect(status().isCreated());
//
//        // Validate the Envoi in the database
//        List<Envoi> envoiList = envoiRepository.findAll();
//        assertThat(envoiList).hasSize(databaseSizeBeforeCreate + 1);
//        Envoi testEnvoi = envoiList.get(envoiList.size() - 1);
//        assertThat(testEnvoi.getDateCreation()).isEqualTo(DEFAULT_DATE_CREATION);
//        assertThat(testEnvoi.getStatut()).isEqualTo(DEFAULT_STATUT);
//        assertThat(testEnvoi.getReference()).isEqualTo(DEFAULT_REFERENCE);
//        assertThat(testEnvoi.getRapportQuai()).isEqualTo(DEFAULT_RAPPORT_QUAI);
//        assertThat(testEnvoi.getRapportLivraisaon()).isEqualTo(DEFAULT_RAPPORT_LIVRAISAON);
//        assertThat(testEnvoi.getMontant()).isEqualTo(DEFAULT_MONTANT);
//    }
//
//    @Test
//    @Transactional
//    public void createEnvoiWithExistingId() throws Exception {
//        int databaseSizeBeforeCreate = envoiRepository.findAll().size();
//
//        // Create the Envoi with an existing ID
//        envoi.setId(1L);
//
//        // An entity with an existing ID cannot be created, so this API call must fail
//        restEnvoiMockMvc.perform(post("/api/envois")
//            .contentType(MediaType.APPLICATION_JSON)
//            .content(TestUtil.convertObjectToJsonBytes(envoi)))
//            .andExpect(status().isBadRequest());
//
//        // Validate the Envoi in the database
//        List<Envoi> envoiList = envoiRepository.findAll();
//        assertThat(envoiList).hasSize(databaseSizeBeforeCreate);
//    }
//
//
//    @Test
//    @Transactional
//    public void getAllEnvois() throws Exception {
//        // Initialize the database
//        envoiRepository.saveAndFlush(envoi);
//
//        // Get all the envoiList
//        restEnvoiMockMvc.perform(get("/api/envois?sort=id,desc"))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
//            .andExpect(jsonPath("$.[*].id").value(hasItem(envoi.getId().intValue())))
//            .andExpect(jsonPath("$.[*].dateCreation").value(hasItem(DEFAULT_DATE_CREATION.toString())))
//            .andExpect(jsonPath("$.[*].statut").value(hasItem(DEFAULT_STATUT.toString())))
//            .andExpect(jsonPath("$.[*].reference").value(hasItem(DEFAULT_REFERENCE)))
//            .andExpect(jsonPath("$.[*].rapportQuai").value(hasItem(DEFAULT_RAPPORT_QUAI)))
//            .andExpect(jsonPath("$.[*].rapportLivraisaon").value(hasItem(DEFAULT_RAPPORT_LIVRAISAON)))
//            .andExpect(jsonPath("$.[*].montant").value(hasItem(DEFAULT_MONTANT.doubleValue())));
//    }
//
//    @Test
//    @Transactional
//    public void getEnvoi() throws Exception {
//        // Initialize the database
//        envoiRepository.saveAndFlush(envoi);
//
//        // Get the envoi
//        restEnvoiMockMvc.perform(get("/api/envois/{id}", envoi.getId()))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
//            .andExpect(jsonPath("$.id").value(envoi.getId().intValue()))
//            .andExpect(jsonPath("$.dateCreation").value(DEFAULT_DATE_CREATION.toString()))
//            .andExpect(jsonPath("$.statut").value(DEFAULT_STATUT.toString()))
//            .andExpect(jsonPath("$.reference").value(DEFAULT_REFERENCE))
//            .andExpect(jsonPath("$.rapportQuai").value(DEFAULT_RAPPORT_QUAI))
//            .andExpect(jsonPath("$.rapportLivraisaon").value(DEFAULT_RAPPORT_LIVRAISAON))
//            .andExpect(jsonPath("$.montant").value(DEFAULT_MONTANT.doubleValue()));
//    }
//    @Test
//    @Transactional
//    public void getNonExistingEnvoi() throws Exception {
//        // Get the envoi
//        restEnvoiMockMvc.perform(get("/api/envois/{id}", Long.MAX_VALUE))
//            .andExpect(status().isNotFound());
//    }
//
//    @Test
//    @Transactional
//    public void updateEnvoi() throws Exception {
//        // Initialize the database
//        envoiRepository.saveAndFlush(envoi);
//
//        int databaseSizeBeforeUpdate = envoiRepository.findAll().size();
//
//        // Update the envoi
//        Envoi updatedEnvoi = envoiRepository.findById(envoi.getId()).get();
//        // Disconnect from session so that the updates on updatedEnvoi are not directly saved in db
//        em.detach(updatedEnvoi);
//        updatedEnvoi
//            .dateCreation(UPDATED_DATE_CREATION)
//            .statut(UPDATED_STATUT)
//            .reference(UPDATED_REFERENCE)
//            .rapportQuai(UPDATED_RAPPORT_QUAI)
//            .rapportLivraisaon(UPDATED_RAPPORT_LIVRAISAON)
//            .montant(UPDATED_MONTANT);
//
//        restEnvoiMockMvc.perform(put("/api/envois")
//            .contentType(MediaType.APPLICATION_JSON)
//            .content(TestUtil.convertObjectToJsonBytes(updatedEnvoi)))
//            .andExpect(status().isOk());
//
//        // Validate the Envoi in the database
//        List<Envoi> envoiList = envoiRepository.findAll();
//        assertThat(envoiList).hasSize(databaseSizeBeforeUpdate);
//        Envoi testEnvoi = envoiList.get(envoiList.size() - 1);
//        assertThat(testEnvoi.getDateCreation()).isEqualTo(UPDATED_DATE_CREATION);
//        assertThat(testEnvoi.getStatut()).isEqualTo(UPDATED_STATUT);
//        assertThat(testEnvoi.getReference()).isEqualTo(UPDATED_REFERENCE);
//        assertThat(testEnvoi.getRapportQuai()).isEqualTo(UPDATED_RAPPORT_QUAI);
//        assertThat(testEnvoi.getRapportLivraisaon()).isEqualTo(UPDATED_RAPPORT_LIVRAISAON);
//        assertThat(testEnvoi.getMontant()).isEqualTo(UPDATED_MONTANT);
//    }
//
//    @Test
//    @Transactional
//    public void updateNonExistingEnvoi() throws Exception {
//        int databaseSizeBeforeUpdate = envoiRepository.findAll().size();
//
//        // If the entity doesn't have an ID, it will throw BadRequestAlertException
//        restEnvoiMockMvc.perform(put("/api/envois")
//            .contentType(MediaType.APPLICATION_JSON)
//            .content(TestUtil.convertObjectToJsonBytes(envoi)))
//            .andExpect(status().isBadRequest());
//
//        // Validate the Envoi in the database
//        List<Envoi> envoiList = envoiRepository.findAll();
//        assertThat(envoiList).hasSize(databaseSizeBeforeUpdate);
//    }
//
//    @Test
//    @Transactional
//    public void deleteEnvoi() throws Exception {
//        // Initialize the database
//        envoiRepository.saveAndFlush(envoi);
//
//        int databaseSizeBeforeDelete = envoiRepository.findAll().size();
//
//        // Delete the envoi
//        restEnvoiMockMvc.perform(delete("/api/envois/{id}", envoi.getId())
//            .accept(MediaType.APPLICATION_JSON))
//            .andExpect(status().isNoContent());
//
//        // Validate the database contains one less item
//        List<Envoi> envoiList = envoiRepository.findAll();
//        assertThat(envoiList).hasSize(databaseSizeBeforeDelete - 1);
//    }
}
