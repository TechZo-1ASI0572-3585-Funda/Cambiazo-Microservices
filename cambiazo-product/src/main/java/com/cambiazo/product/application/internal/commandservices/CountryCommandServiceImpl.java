package com.cambiazo.product.application.internal.commandservices;

import com.cambiazo.product.domain.model.commands.CreateCountryCommand;
import com.cambiazo.product.domain.model.entities.Country;
import com.cambiazo.product.domain.services.ICountryCommandService;
import com.cambiazo.product.infrastructure.persistence.jpa.ICountryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CountryCommandServiceImpl implements ICountryCommandService {
    private final ICountryRepository countryRepository;


    public CountryCommandServiceImpl(ICountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public Optional<Country>handle(CreateCountryCommand command) {
        if (countryRepository.existsByName(command.name())) {
            throw new IllegalArgumentException("Country with same name already exists");
        }
        var country = new Country(command);
        var createdCountry = countryRepository.save(country);
        return Optional.of(createdCountry);
    }
}
