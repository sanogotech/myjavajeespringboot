package com.gs2e.audiencier.repository;

import com.gs2e.audiencier.domain.Avocat;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Avocat entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AvocatRepository extends JpaRepository<Avocat, Long> {
}
