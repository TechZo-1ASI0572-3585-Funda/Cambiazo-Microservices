package com.cambiazo.user.infrastructure.persistence.jpa.repositories;

import com.cambiazo.user.domain.model.entities.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPlanRepository extends JpaRepository<Plan, Long> {
    boolean existsByName(String name);
}
