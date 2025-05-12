package com.cambiazo.product.domain.services;

import com.cambiazo.product.domain.model.entities.Country;
import com.cambiazo.product.domain.model.queries.GetAllCountriesQuery;
import com.cambiazo.product.domain.model.queries.GetCountryByIdQuery;

import java.util.List;
import java.util.Optional;

public interface ICountryQueryService {
    Optional<Country>handle(GetCountryByIdQuery query);

    List<Country>handle(GetAllCountriesQuery query);
}
