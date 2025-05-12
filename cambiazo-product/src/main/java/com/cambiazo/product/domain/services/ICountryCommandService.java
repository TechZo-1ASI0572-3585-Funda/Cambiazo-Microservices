package com.cambiazo.product.domain.services;

import com.cambiazo.product.domain.model.commands.CreateCountryCommand;
import com.cambiazo.product.domain.model.entities.Country;

import java.util.Optional;

public interface ICountryCommandService {
    Optional<Country> handle(CreateCountryCommand command);
}
