package com.cambiazo.user.application.internal.queryservices;

import com.cambiazo.user.domain.model.dto.PlanDto;
import com.cambiazo.user.domain.model.entities.Plan;
import com.cambiazo.user.domain.model.queries.GetAllPlansQuery;
import com.cambiazo.user.domain.model.queries.GetPlanByIdQuery;
import com.cambiazo.user.domain.services.IPlanQueryService;
import com.cambiazo.user.infrastructure.persistence.jpa.repositories.IBenefitRepository;
import com.cambiazo.user.infrastructure.persistence.jpa.repositories.IPlanRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PlanQueryServiceImpl implements IPlanQueryService {

    private final IPlanRepository planRepository;

    private final IBenefitRepository benefitRepository;


    public PlanQueryServiceImpl(IPlanRepository planRepository, IBenefitRepository benefitRepository){

        this.planRepository=planRepository;
        this.benefitRepository=benefitRepository;
    }


    @Override
    public Optional<PlanDto> handle(GetPlanByIdQuery query) {

        Plan plan = this.planRepository.findById(query.id()).orElseThrow(() -> new IllegalArgumentException("Plan with id "+query.id()+" not found"));
        return Optional.of(new PlanDto(plan, this.benefitRepository.findBenefitsByPlanId(plan)));
    }

    @Override
    public List<PlanDto> handle(GetAllPlansQuery query) {
        List<Plan>plans = this.planRepository.findAll();

        return plans.stream().map(plan->
                new PlanDto(plan, this.benefitRepository.findBenefitsByPlanId(plan))
        ).toList();
    }
}