package com.cambiazo.product.interfaces.rest.transform;

import com.cambiazo.product.domain.model.commands.CreateCountryCommand;
import com.cambiazo.product.interfaces.rest.resources.CreateCountryResource;

public class CreateCountryCommandFromResourceAssembler {
    public static CreateCountryCommand toCommandFromResource(CreateCountryResource resource) {
        return new CreateCountryCommand(resource.name());
    }
}
