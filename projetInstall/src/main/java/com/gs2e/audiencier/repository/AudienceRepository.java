package com.gs2e.audiencier.repository;

import com.gs2e.audiencier.domain.Audience;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Audience entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AudienceRepository extends JpaRepository<Audience, Long> {
	
	public List<Audience> findByStade(String stade);
	
	public List<Audience>  findByContentieux_Id(Long id);
}
