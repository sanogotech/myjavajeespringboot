package com.gs2e.audiencier.repository;

import com.gs2e.audiencier.domain.Juridiction;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Juridiction entity.
 */
@SuppressWarnings("unused")
@Repository
public interface JuridictionRepository extends JpaRepository<Juridiction, Long> {
}
