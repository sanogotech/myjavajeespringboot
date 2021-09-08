package com.gs2e.audiencier.web.rest;

import com.gs2e.audiencier.domain.Audience;
import com.gs2e.audiencier.service.AudienceService;
import com.gs2e.audiencier.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.gs2e.audiencier.domain.Audience}.
 */
@RestController
@RequestMapping("/api")
public class AudienceResource {

    private final Logger log = LoggerFactory.getLogger(AudienceResource.class);

    private static final String ENTITY_NAME = "audience";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AudienceService audienceService;

    public AudienceResource(AudienceService audienceService) {
        this.audienceService = audienceService;
    }

    /**
     * {@code POST  /audiences} : Create a new audience.
     *
     * @param audience the audience to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new audience, or with status {@code 400 (Bad Request)} if the audience has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/audiences")
    public ResponseEntity<Audience> createAudience(@Valid @RequestBody Audience audience) throws URISyntaxException {
        log.debug("REST request to save Audience : {}", audience);
        if (audience.getId() != null) {
            throw new BadRequestAlertException("A new audience cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Audience result = audienceService.save(audience);
        return ResponseEntity.created(new URI("/api/audiences/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /audiences} : Updates an existing audience.
     *
     * @param audience the audience to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated audience,
     * or with status {@code 400 (Bad Request)} if the audience is not valid,
     * or with status {@code 500 (Internal Server Error)} if the audience couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/audiences")
    public ResponseEntity<Audience> updateAudience(@Valid @RequestBody Audience audience) throws URISyntaxException {
        log.debug("REST request to update Audience : {}", audience);
        if (audience.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Audience result = audienceService.save(audience);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, audience.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /audiences} : get all the audiences.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of audiences in body.
     */
    @GetMapping("/audiences")
    public ResponseEntity<List<Audience>> getAllAudiences(Pageable pageable) {
        log.debug("REST request to get a page of Audiences");
        Page<Audience> page = audienceService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }


	@GetMapping("/contentieux/{id}/audiences")
    public ResponseEntity<List<Audience>> getAllAudiences(@PathVariable Long id) {
        log.debug("Rest request  audiencesByContentieuxId ...");

		 List<Audience> resultListAudiences = audienceService.findByContentieuxId(id);
		  log.debug("***** Display all audiences ..." +resultListAudiences);
        return ResponseEntity.ok().body(resultListAudiences);
    }

    /**
     * {@code GET  /audiences/:id} : get the "id" audience.
     *
     * @param id the id of the audience to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the audience, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/audiences/{id}")
    public ResponseEntity<Audience> getAudience(@PathVariable Long id) {
        log.debug("REST request to get Audience : {}", id);
        Optional<Audience> audience = audienceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(audience);
    }

    /**
     * {@code DELETE  /audiences/:id} : delete the "id" audience.
     *
     * @param id the id of the audience to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/audiences/{id}")
    public ResponseEntity<Void> deleteAudience(@PathVariable Long id) {
        log.debug("REST request to delete Audience : {}", id);
        audienceService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
