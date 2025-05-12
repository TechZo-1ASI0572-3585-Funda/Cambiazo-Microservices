package com.cambiazo.product.interfaces.rest.transform;

import com.cambiazo.product.domain.model.entities.Country;
import com.cambiazo.product.interfaces.rest.resources.CountryResource;

public class CountryResourceFromEntityAssembler {
    public static CountryResource toResourceFromEntity(Country entity) {
        return new CountryResource(entity.getId(), entity.getName());
    }
}
