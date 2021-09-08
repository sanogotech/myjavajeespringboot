package com.gs2e.audiencier.service;

import com.gs2e.audiencier.domain.Audience;
import com.gs2e.audiencier.repository.AudienceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import java.util.List;

/**
 * Service Implementation for managing {@link Audience}.
 */
@Service
@Transactional
public class AudienceService {

    private final Logger log = LoggerFactory.getLogger(AudienceService.class);

    private final AudienceRepository audienceRepository;

    public AudienceService(AudienceRepository audienceRepository) {
        this.audienceRepository = audienceRepository;
    }

    /**
     * Save a audience.
     *
     * @param audience the entity to save.
     * @return the persisted entity.
     */
    public Audience save(Audience audience) {
        log.debug("Request to save Audience : {}", audience);
        return audienceRepository.save(audience);
    }

    /**
     * Get all the audiences.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Audience> findAll(Pageable pageable) {
        log.debug("Request to get all Audiences");
        return audienceRepository.findAll(pageable);
    }


    /**
     * Get one audience by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Audience> findOne(Long id) {
        log.debug("Request to get Audience : {}", id);
        return audienceRepository.findById(id);
    }
	
	
	//  New +++
	@Transactional(readOnly = true)
    public List<Audience>  findByContentieuxId(Long id) {
	
        log.debug("Request to get Audience by Contentieux Id : {}", id);
		//Test
		String  stade  ="mystade";
		audienceRepository.findByStade(stade);
        return audienceRepository.findByContentieux_Id(id);
    }
	
	

    /**
     * Delete the audience by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Audience : {}", id);
        audienceRepository.deleteById(id);
    }
}
