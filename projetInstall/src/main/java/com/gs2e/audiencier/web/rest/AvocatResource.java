package com.gs2e.audiencier.web.rest;

import com.gs2e.audiencier.domain.Avocat;
import com.gs2e.audiencier.repository.AvocatRepository;
import com.gs2e.audiencier.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.gs2e.audiencier.domain.Avocat}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class AvocatResource {

    private final Logger log = LoggerFactory.getLogger(AvocatResource.class);

    private static final String ENTITY_NAME = "avocat";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AvocatRepository avocatRepository;

    public AvocatResource(AvocatRepository avocatRepository) {
        this.avocatRepository = avocatRepository;
    }

    /**
     * {@code POST  /avocats} : Create a new avocat.
     *
     * @param avocat the avocat to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new avocat, or with status {@code 400 (Bad Request)} if the avocat has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/avocats")
    public ResponseEntity<Avocat> createAvocat(@Valid @RequestBody Avocat avocat) throws URISyntaxException {
        log.debug("REST request to save Avocat : {}", avocat);
        if (avocat.getId() != null) {
            throw new BadRequestAlertException("A new avocat cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Avocat result = avocatRepository.save(avocat);
        return ResponseEntity.created(new URI("/api/avocats/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /avocats} : Updates an existing avocat.
     *
     * @param avocat the avocat to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated avocat,
     * or with status {@code 400 (Bad Request)} if the avocat is not valid,
     * or with status {@code 500 (Internal Server Error)} if the avocat couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/avocats")
    public ResponseEntity<Avocat> updateAvocat(@Valid @RequestBody Avocat avocat) throws URISyntaxException {
        log.debug("REST request to update Avocat : {}", avocat);
        if (avocat.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Avocat result = avocatRepository.save(avocat);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, avocat.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /avocats} : get all the avocats.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of avocats in body.
     */
    @GetMapping("/avocats")
    public List<Avocat> getAllAvocats() {
        log.debug("REST request to get all Avocats");
        return avocatRepository.findAll();
    }

    /**
     * {@code GET  /avocats/:id} : get the "id" avocat.
     *
     * @param id the id of the avocat to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the avocat, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/avocats/{id}")
    public ResponseEntity<Avocat> getAvocat(@PathVariable Long id) {
        log.debug("REST request to get Avocat : {}", id);
        Optional<Avocat> avocat = avocatRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(avocat);
    }

    /**
     * {@code DELETE  /avocats/:id} : delete the "id" avocat.
     *
     * @param id the id of the avocat to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/avocats/{id}")
    public ResponseEntity<Void> deleteAvocat(@PathVariable Long id) {
        log.debug("REST request to delete Avocat : {}", id);
        avocatRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
