package com.gs2e.audiencier.web.rest;

import com.gs2e.audiencier.domain.Requerant;
import com.gs2e.audiencier.repository.RequerantRepository;
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
 * REST controller for managing {@link com.gs2e.audiencier.domain.Requerant}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class RequerantResource {

    private final Logger log = LoggerFactory.getLogger(RequerantResource.class);

    private static final String ENTITY_NAME = "requerant";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RequerantRepository requerantRepository;

    public RequerantResource(RequerantRepository requerantRepository) {
        this.requerantRepository = requerantRepository;
    }

    /**
     * {@code POST  /requerants} : Create a new requerant.
     *
     * @param requerant the requerant to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new requerant, or with status {@code 400 (Bad Request)} if the requerant has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/requerants")
    public ResponseEntity<Requerant> createRequerant(@Valid @RequestBody Requerant requerant) throws URISyntaxException {
        log.debug("REST request to save Requerant : {}", requerant);
        if (requerant.getId() != null) {
            throw new BadRequestAlertException("A new requerant cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Requerant result = requerantRepository.save(requerant);
        return ResponseEntity.created(new URI("/api/requerants/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /requerants} : Updates an existing requerant.
     *
     * @param requerant the requerant to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated requerant,
     * or with status {@code 400 (Bad Request)} if the requerant is not valid,
     * or with status {@code 500 (Internal Server Error)} if the requerant couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/requerants")
    public ResponseEntity<Requerant> updateRequerant(@Valid @RequestBody Requerant requerant) throws URISyntaxException {
        log.debug("REST request to update Requerant : {}", requerant);
        if (requerant.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Requerant result = requerantRepository.save(requerant);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, requerant.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /requerants} : get all the requerants.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of requerants in body.
     */
    @GetMapping("/requerants")
    public List<Requerant> getAllRequerants() {
        log.debug("REST request to get all Requerants");
        return requerantRepository.findAll();
    }

    /**
     * {@code GET  /requerants/:id} : get the "id" requerant.
     *
     * @param id the id of the requerant to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the requerant, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/requerants/{id}")
    public ResponseEntity<Requerant> getRequerant(@PathVariable Long id) {
        log.debug("REST request to get Requerant : {}", id);
        Optional<Requerant> requerant = requerantRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(requerant);
    }

    /**
     * {@code DELETE  /requerants/:id} : delete the "id" requerant.
     *
     * @param id the id of the requerant to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/requerants/{id}")
    public ResponseEntity<Void> deleteRequerant(@PathVariable Long id) {
        log.debug("REST request to delete Requerant : {}", id);
        requerantRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
