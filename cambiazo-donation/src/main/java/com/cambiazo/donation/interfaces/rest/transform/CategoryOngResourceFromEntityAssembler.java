package com.cambiazo.donation.interfaces.rest.transform;

import com.cambiazo.donation.domain.model.entities.CategoryOng;
import com.cambiazo.donation.interfaces.rest.resources.CategoryOngResource;

public class CategoryOngResourceFromEntityAssembler {
    public static CategoryOngResource toResourceFromEntity(CategoryOng entity) {
        return new CategoryOngResource(entity.getId(), entity.getName());
    }
}
