package com.cambiazo.user.infrastructure.persistence.jpa.repositories;

import com.cambiazo.user.domain.model.entities.Benefit;
import com.cambiazo.user.domain.model.entities.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBenefitRepository extends JpaRepository<Benefit, Long>{

    List<Benefit>findBenefitsByPlanId(Plan planId);
}
