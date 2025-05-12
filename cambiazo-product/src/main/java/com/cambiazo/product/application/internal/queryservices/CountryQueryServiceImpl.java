package com.cambiazo.product.application.internal.queryservices;

import com.cambiazo.product.domain.model.entities.Country;
import com.cambiazo.product.domain.model.queries.GetAllCountriesQuery;
import com.cambiazo.product.domain.model.queries.GetCountryByIdQuery;
import com.cambiazo.product.domain.services.ICountryQueryService;
import com.cambiazo.product.infrastructure.persistence.jpa.ICountryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryQueryServiceImpl implements ICountryQueryService {

    private final ICountryRepository countryRepository;

    public CountryQueryServiceImpl(ICountryRepository countryRepository){
        this.countryRepository=countryRepository;
    }

    @Override
    public List<Country> handle(GetAllCountriesQuery query){
        return countryRepository.findAll();
    }

    @Override
    public Optional<Country> handle(GetCountryByIdQuery query) {
        return countryRepository.findById(query.id());
    }
}
