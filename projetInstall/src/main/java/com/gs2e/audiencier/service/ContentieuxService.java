package com.gs2e.audiencier.service;

import com.gs2e.audiencier.domain.Contentieux;
import com.gs2e.audiencier.repository.ContentieuxRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import  java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

/**
 * Service Implementation for managing {@link Contentieux}.
 */
@Service
@Transactional
public class ContentieuxService {

    private final Logger log = LoggerFactory.getLogger(ContentieuxService.class);

    private final ContentieuxRepository contentieuxRepository;

    public ContentieuxService(ContentieuxRepository contentieuxRepository) {
        this.contentieuxRepository = contentieuxRepository;
    }

    /**
     * Save a contentieux.
     *
     * @param contentieux the entity to save.
     * @return the persisted entity.
     */
    public Contentieux save(Contentieux contentieux) {
        log.debug("Request to save Contentieux : {}", contentieux);
		 String refContentieux = contentieux.getRequerant().getNom().toUpperCase() + "_"+ contentieux.getEntite().name()+"_"+ LocalDateTime.now();
		 contentieux.setRefContentieux(refContentieux);
        return contentieuxRepository.save(contentieux);
    }

    /**
     * Get all the contentieuxes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Contentieux> findAll(Pageable pageable) {
        log.debug("Request to get all Contentieuxes");
        return contentieuxRepository.findAll(pageable);
    }


    /**
     * Get one contentieux by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Contentieux> findOne(Long id) {
        log.debug("Request to get Contentieux : {}", id);
        return contentieuxRepository.findById(id);
    }
	
	
	@Transactional(readOnly = true)
    public List<Contentieux> findAllByContentieuxId(Long id) {
        log.debug("Request to get Contentieux : {}", id);
		List<Contentieux>  mylistContentieux = new  ArrayList<Contentieux>();
		Contentieux contentieux = contentieuxRepository.findById(id).get();
		mylistContentieux.add(contentieux);
		
		  log.debug("mylistContentieux : {}", mylistContentieux);
        return mylistContentieux;
    }

    /**
     * Delete the contentieux by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Contentieux : {}", id);
        contentieuxRepository.deleteById(id);
    }
}
