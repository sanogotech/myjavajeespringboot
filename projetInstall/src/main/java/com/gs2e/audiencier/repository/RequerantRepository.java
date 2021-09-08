package com.gs2e.audiencier.repository;

import com.gs2e.audiencier.domain.Requerant;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Requerant entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RequerantRepository extends JpaRepository<Requerant, Long> {
}
