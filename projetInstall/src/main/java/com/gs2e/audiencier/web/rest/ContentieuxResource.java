package com.gs2e.audiencier.web.rest;

import com.gs2e.audiencier.domain.Contentieux;
import com.gs2e.audiencier.service.ContentieuxService;
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
 * REST controller for managing {@link com.gs2e.audiencier.domain.Contentieux}.
 */
@RestController
@RequestMapping("/api")
public class ContentieuxResource {

    private final Logger log = LoggerFactory.getLogger(ContentieuxResource.class);

    private static final String ENTITY_NAME = "contentieux";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ContentieuxService contentieuxService;

    public ContentieuxResource(ContentieuxService contentieuxService) {
        this.contentieuxService = contentieuxService;
    }

    /**
     * {@code POST  /contentieuxes} : Create a new contentieux.
     *
     * @param contentieux the contentieux to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new contentieux, or with status {@code 400 (Bad Request)} if the contentieux has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/contentieuxes")
    public ResponseEntity<Contentieux> createContentieux(@Valid @RequestBody Contentieux contentieux) throws URISyntaxException {
        log.debug("REST request to save Contentieux : {}", contentieux);
        if (contentieux.getId() != null) {
            throw new BadRequestAlertException("A new contentieux cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Contentieux result = contentieuxService.save(contentieux);
        return ResponseEntity.created(new URI("/api/contentieuxes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /contentieuxes} : Updates an existing contentieux.
     *
     * @param contentieux the contentieux to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated contentieux,
     * or with status {@code 400 (Bad Request)} if the contentieux is not valid,
     * or with status {@code 500 (Internal Server Error)} if the contentieux couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/contentieuxes")
    public ResponseEntity<Contentieux> updateContentieux(@Valid @RequestBody Contentieux contentieux) throws URISyntaxException {
        log.debug("REST request to update Contentieux : {}", contentieux);
        if (contentieux.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Contentieux result = contentieuxService.save(contentieux);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, contentieux.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /contentieuxes} : get all the contentieuxes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of contentieuxes in body.
     */
    @GetMapping("/contentieuxes")
    public ResponseEntity<List<Contentieux>> getAllContentieuxes(Pageable pageable) {
        log.debug("REST request to get a page of Contentieuxes");
        Page<Contentieux> page = contentieuxService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
	
	//New ++
	@GetMapping("/contentieuxesAllById/{id}")
    public ResponseEntity<List<Contentieux>> retrieveByContentieuxId(@PathVariable Long id) {
       
	    List<Contentieux> resultListContentieux = contentieuxService.findAllByContentieuxId(id);

        return ResponseEntity.ok().body(resultListContentieux);
    }

    /**
     * {@code GET  /contentieuxes/:id} : get the "id" contentieux.
     *
     * @param id the id of the contentieux to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the contentieux, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/contentieuxes/{id}")
    public ResponseEntity<Contentieux> getContentieux(@PathVariable Long id) {
        log.debug("REST request to get Contentieux : {}", id);
        Optional<Contentieux> contentieux = contentieuxService.findOne(id);
        return ResponseUtil.wrapOrNotFound(contentieux);
    }

    /**
     * {@code DELETE  /contentieuxes/:id} : delete the "id" contentieux.
     *
     * @param id the id of the contentieux to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/contentieuxes/{id}")
    public ResponseEntity<Void> deleteContentieux(@PathVariable Long id) {
        log.debug("REST request to delete Contentieux : {}", id);
        contentieuxService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
