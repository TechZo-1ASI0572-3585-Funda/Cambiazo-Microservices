package com.cambiazo.user.domain.services;

import com.cambiazo.user.domain.model.commands.CreateBenefitCommand;
import com.cambiazo.user.domain.model.entities.Benefit;

import java.util.Optional;

public interface IBenefitCommandService {

    Optional<Benefit>handle(CreateBenefitCommand command);
}
