package com.cambiazo.user.application.internal.commandservices;

import com.cambiazo.user.domain.model.commands.CreateBenefitCommand;
import com.cambiazo.user.domain.model.entities.Benefit;
import com.cambiazo.user.domain.model.entities.Plan;
import com.cambiazo.user.domain.services.IBenefitCommandService;
import com.cambiazo.user.infrastructure.persistence.jpa.repositories.IBenefitRepository;
import com.cambiazo.user.infrastructure.persistence.jpa.repositories.IPlanRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class BenefitCommandServiceImpl implements IBenefitCommandService {

    private final IBenefitRepository benefitRepository;

    private final IPlanRepository planRepository;


    public BenefitCommandServiceImpl(IBenefitRepository benefitRepository, IPlanRepository planRepository) {
        this.benefitRepository = benefitRepository;
        this.planRepository = planRepository;
    }


    @Override
    public Optional<Benefit> handle(CreateBenefitCommand command) {
        Plan plan = planRepository.findById(command.planId()).orElseThrow(() -> new IllegalArgumentException("Plan with same id already exists"));
        var benefit = new Benefit(command, plan);
        benefitRepository.save(benefit);
        return Optional.of(benefit);
    }
}
