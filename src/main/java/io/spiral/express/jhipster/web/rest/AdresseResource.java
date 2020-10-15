package io.spiral.express.jhipster.web.rest;

import io.spiral.express.jhipster.domain.Adresse;
import io.spiral.express.jhipster.repository.AdresseRepository;
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
 * REST controller for managing {@link io.spiral.express.jhipster.domain.Adresse}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class AdresseResource {

    private final Logger log = LoggerFactory.getLogger(AdresseResource.class);

    private static final String ENTITY_NAME = "adresse";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AdresseRepository adresseRepository;

    public AdresseResource(AdresseRepository adresseRepository) {
        this.adresseRepository = adresseRepository;
    }

    /**
     * {@code POST  /adresses} : Create a new adresse.
     *
     * @param adresse the adresse to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new adresse, or with status {@code 400 (Bad Request)} if the adresse has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/adresses")
    public ResponseEntity<Adresse> createAdresse(@RequestBody Adresse adresse) throws URISyntaxException {
        log.debug("REST request to save Adresse : {}", adresse);
        if (adresse.getId() != null) {
            throw new BadRequestAlertException("A new adresse cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Adresse result = adresseRepository.save(adresse);
        return ResponseEntity.created(new URI("/api/adresses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /adresses} : Updates an existing adresse.
     *
     * @param adresse the adresse to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated adresse,
     * or with status {@code 400 (Bad Request)} if the adresse is not valid,
     * or with status {@code 500 (Internal Server Error)} if the adresse couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/adresses")
    public ResponseEntity<Adresse> updateAdresse(@RequestBody Adresse adresse) throws URISyntaxException {
        log.debug("REST request to update Adresse : {}", adresse);
        if (adresse.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Adresse result = adresseRepository.save(adresse);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, adresse.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /adresses} : get all the adresses.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of adresses in body.
     */
    @GetMapping("/adresses")
    public List<Adresse> getAllAdresses() {
        log.debug("REST request to get all Adresses");
        return adresseRepository.findAll();
    }

    /**
     * {@code GET  /adresses/:id} : get the "id" adresse.
     *
     * @param id the id of the adresse to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the adresse, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/adresses/{id}")
    public ResponseEntity<Adresse> getAdresse(@PathVariable Long id) {
        log.debug("REST request to get Adresse : {}", id);
        Optional<Adresse> adresse = adresseRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(adresse);
    }

    /**
     * {@code DELETE  /adresses/:id} : delete the "id" adresse.
     *
     * @param id the id of the adresse to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/adresses/{id}")
    public ResponseEntity<Void> deleteAdresse(@PathVariable Long id) {
        log.debug("REST request to delete Adresse : {}", id);
        adresseRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
