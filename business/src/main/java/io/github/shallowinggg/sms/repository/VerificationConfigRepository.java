package io.github.shallowinggg.sms.repository;

import io.github.shallowinggg.sms.model.VerificationConfig;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author ding shimin
 */
@Repository
public interface VerificationConfigRepository extends JpaRepository<VerificationConfig, Long> {

    Optional<VerificationConfig> findById(long id);

    Page<VerificationConfig> findAllByNameContaining(String name, Pageable pageable);

    //int countAll(Specification<VerificationConfig> specification);

    Page<VerificationConfig> findAll(Specification<VerificationConfig> specification, Pageable pageable);

}
