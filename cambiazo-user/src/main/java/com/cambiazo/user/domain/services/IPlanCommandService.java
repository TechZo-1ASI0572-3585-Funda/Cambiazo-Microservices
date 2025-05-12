package com.cambiazo.user.domain.services;

import com.cambiazo.user.domain.model.commands.CreatePlanCommand;
import com.cambiazo.user.domain.model.entities.Plan;

import java.util.Optional;

public interface IPlanCommandService {
    Optional<Plan> handle(CreatePlanCommand command);
}
