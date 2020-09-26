package io.spiral.express.jhipster.web.rest;

import io.spiral.express.jhipster.domain.Coli;
import io.spiral.express.jhipster.repository.ColiRepository;
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
 * REST controller for managing {@link io.spiral.express.jhipster.domain.Coli}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ColiResource {

    private final Logger log = LoggerFactory.getLogger(ColiResource.class);

    private static final String ENTITY_NAME = "coli";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ColiRepository coliRepository;

    public ColiResource(ColiRepository coliRepository) {
        this.coliRepository = coliRepository;
    }

    /**
     * {@code POST  /colis} : Create a new coli.
     *
     * @param coli the coli to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new coli, or with status {@code 400 (Bad Request)} if the coli has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/colis")
    public ResponseEntity<Coli> createColi(@RequestBody Coli coli) throws URISyntaxException {
        log.debug("REST request to save Coli : {}", coli);
        if (coli.getId() != null) {
            throw new BadRequestAlertException("A new coli cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Coli result = coliRepository.save(coli);
        return ResponseEntity.created(new URI("/api/colis/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /colis} : Updates an existing coli.
     *
     * @param coli the coli to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated coli,
     * or with status {@code 400 (Bad Request)} if the coli is not valid,
     * or with status {@code 500 (Internal Server Error)} if the coli couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/colis")
    public ResponseEntity<Coli> updateColi(@RequestBody Coli coli) throws URISyntaxException {
        log.debug("REST request to update Coli : {}", coli);
        if (coli.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Coli result = coliRepository.save(coli);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, coli.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /colis} : get all the colis.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of colis in body.
     */
    @GetMapping("/colis")
    public List<Coli> getAllColis() {
        log.debug("REST request to get all Colis");
        return coliRepository.findAll();
    }

    /**
     * {@code GET  /colis/:id} : get the "id" coli.
     *
     * @param id the id of the coli to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the coli, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/colis/{id}")
    public ResponseEntity<Coli> getColi(@PathVariable Long id) {
        log.debug("REST request to get Coli : {}", id);
        Optional<Coli> coli = coliRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(coli);
    }

    /**
     * {@code DELETE  /colis/:id} : delete the "id" coli.
     *
     * @param id the id of the coli to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/colis/{id}")
    public ResponseEntity<Void> deleteColi(@PathVariable Long id) {
        log.debug("REST request to delete Coli : {}", id);
        coliRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
