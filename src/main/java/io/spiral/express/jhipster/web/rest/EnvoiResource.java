package io.spiral.express.jhipster.web.rest;

import io.spiral.express.jhipster.domain.Envoi;
import io.spiral.express.jhipster.repository.EnvoiRepository;
import io.spiral.express.jhipster.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link io.spiral.express.jhipster.domain.Envoi}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class EnvoiResource {

    private final Logger log = LoggerFactory.getLogger(EnvoiResource.class);

    private static final String ENTITY_NAME = "envoi";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EnvoiRepository envoiRepository;

    public EnvoiResource(EnvoiRepository envoiRepository) {
        this.envoiRepository = envoiRepository;
    }

    /**
     * {@code POST  /envois} : Create a new envoi.
     *
     * @param envoi the envoi to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new envoi, or with status {@code 400 (Bad Request)} if the envoi has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/envois")
    public ResponseEntity<Envoi> createEnvoi(@RequestBody Envoi envoi) throws URISyntaxException {
        log.debug("REST request to save Envoi : {}", envoi);
        if (envoi.getId() != null) {
            throw new BadRequestAlertException("A new envoi cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Envoi result = envoiRepository.save(envoi);
        return ResponseEntity.created(new URI("/api/envois/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /envois} : Updates an existing envoi.
     *
     * @param envoi the envoi to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated envoi,
     * or with status {@code 400 (Bad Request)} if the envoi is not valid,
     * or with status {@code 500 (Internal Server Error)} if the envoi couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/envois")
    public ResponseEntity<Envoi> updateEnvoi(@RequestBody Envoi envoi) throws URISyntaxException {
        log.debug("REST request to update Envoi : {}", envoi);
        if (envoi.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Envoi result = envoiRepository.save(envoi);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, envoi.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /envois} : get all the envois.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of envois in body.
     */
    @GetMapping("/envois")
    public List<Envoi> getAllEnvois() {
        log.debug("REST request to get all Envois");
        return envoiRepository.findAll();
    }

    /**
     * {@code GET  /envois/:id} : get the "id" envoi.
     *
     * @param id the id of the envoi to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the envoi, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/envois/{id}")
    public ResponseEntity<Envoi> getEnvoi(@PathVariable Long id) {
        log.debug("REST request to get Envoi : {}", id);
        Optional<Envoi> envoi = envoiRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(envoi);
    }

    /**
     * {@code DELETE  /envois/:id} : delete the "id" envoi.
     *
     * @param id the id of the envoi to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/envois/{id}")
    public ResponseEntity<Void> deleteEnvoi(@PathVariable Long id) {
        log.debug("REST request to delete Envoi : {}", id);
        envoiRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
