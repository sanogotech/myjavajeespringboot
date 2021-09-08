package com.gs2e.audiencier.repository;

import com.gs2e.audiencier.domain.Contentieux;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Contentieux entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ContentieuxRepository extends JpaRepository<Contentieux, Long> {
}
