package com.cambiazo.donation.interfaces.rest.transform;

import com.cambiazo.donation.domain.model.commands.CreateCategoryOngCommand;
import com.cambiazo.donation.interfaces.rest.resources.CreateCategoryOngResource;

public class CreateCategoryOngCommandFromResourceAssembler {
    public static CreateCategoryOngCommand toCommandFromResource(CreateCategoryOngResource resource) {
        return new CreateCategoryOngCommand(resource.name());
    }
}
