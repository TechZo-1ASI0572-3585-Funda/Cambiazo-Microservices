package com.cambiazo.donation.interfaces.rest.transform;

import com.cambiazo.donation.domain.model.commands.UpdateCategoryOngCommand;
import com.cambiazo.donation.interfaces.rest.resources.UpdateCategoryOngResource;

public class UpdateCategoryOngCommandFromResourceAssembler {

    public static UpdateCategoryOngCommand toCommandFromResource(Long categoryId, UpdateCategoryOngResource resource) {
        return new UpdateCategoryOngCommand(categoryId, resource.name());
    }
}
