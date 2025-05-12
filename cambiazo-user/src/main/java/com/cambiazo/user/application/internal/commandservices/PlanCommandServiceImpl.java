package com.cambiazo.user.application.internal.commandservices;

import com.cambiazo.user.domain.model.commands.CreatePlanCommand;
import com.cambiazo.user.domain.model.entities.Plan;
import com.cambiazo.user.domain.services.IPlanCommandService;
import com.cambiazo.user.infrastructure.persistence.jpa.repositories.IPlanRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlanCommandServiceImpl implements IPlanCommandService {

    private final IPlanRepository planRepository;

    public PlanCommandServiceImpl(IPlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    @Override
    public Optional<Plan>handle(CreatePlanCommand command){
        if(planRepository.existsByName(command.name())){
            throw new IllegalArgumentException("Plan with same name already exists");
        }
        var plan = new Plan(command);
        var createdPlan = planRepository.save(plan);
        return Optional.of(createdPlan);
    }

}
