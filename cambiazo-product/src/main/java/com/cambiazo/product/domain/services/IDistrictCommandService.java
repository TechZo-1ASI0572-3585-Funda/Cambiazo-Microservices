package com.cambiazo.product.domain.services;

import com.cambiazo.product.domain.model.commands.CreateDistrictCommand;
import com.cambiazo.product.domain.model.entities.District;

import java.util.Optional;

public interface IDistrictCommandService {
    Optional<District>handle(CreateDistrictCommand command);
}
