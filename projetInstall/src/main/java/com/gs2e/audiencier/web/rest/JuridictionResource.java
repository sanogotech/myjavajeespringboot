package com.gs2e.audiencier.web.rest;

import com.gs2e.audiencier.domain.Juridiction;
import com.gs2e.audiencier.repository.JuridictionRepository;
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
 * REST controller for managing {@link com.gs2e.audiencier.domain.Juridiction}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class JuridictionResource {

    private final Logger log = LoggerFactory.getLogger(JuridictionResource.class);

    private static final String ENTITY_NAME = "juridiction";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final JuridictionRepository juridictionRepository;

    public JuridictionResource(JuridictionRepository juridictionRepository) {
        this.juridictionRepository = juridictionRepository;
    }

    /**
     * {@code POST  /juridictions} : Create a new juridiction.
     *
     * @param juridiction the juridiction to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new juridiction, or with status {@code 400 (Bad Request)} if the juridiction has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/juridictions")
    public ResponseEntity<Juridiction> createJuridiction(@Valid @RequestBody Juridiction juridiction) throws URISyntaxException {
        log.debug("REST request to save Juridiction : {}", juridiction);
        if (juridiction.getId() != null) {
            throw new BadRequestAlertException("A new juridiction cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Juridiction result = juridictionRepository.save(juridiction);
        return ResponseEntity.created(new URI("/api/juridictions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /juridictions} : Updates an existing juridiction.
     *
     * @param juridiction the juridiction to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated juridiction,
     * or with status {@code 400 (Bad Request)} if the juridiction is not valid,
     * or with status {@code 500 (Internal Server Error)} if the juridiction couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/juridictions")
    public ResponseEntity<Juridiction> updateJuridiction(@Valid @RequestBody Juridiction juridiction) throws URISyntaxException {
        log.debug("REST request to update Juridiction : {}", juridiction);
        if (juridiction.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Juridiction result = juridictionRepository.save(juridiction);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, juridiction.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /juridictions} : get all the juridictions.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of juridictions in body.
     */
    @GetMapping("/juridictions")
    public List<Juridiction> getAllJuridictions() {
        log.debug("REST request to get all Juridictions");
        return juridictionRepository.findAll();
    }

    /**
     * {@code GET  /juridictions/:id} : get the "id" juridiction.
     *
     * @param id the id of the juridiction to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the juridiction, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/juridictions/{id}")
    public ResponseEntity<Juridiction> getJuridiction(@PathVariable Long id) {
        log.debug("REST request to get Juridiction : {}", id);
        Optional<Juridiction> juridiction = juridictionRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(juridiction);
    }

    /**
     * {@code DELETE  /juridictions/:id} : delete the "id" juridiction.
     *
     * @param id the id of the juridiction to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/juridictions/{id}")
    public ResponseEntity<Void> deleteJuridiction(@PathVariable Long id) {
        log.debug("REST request to delete Juridiction : {}", id);
        juridictionRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
