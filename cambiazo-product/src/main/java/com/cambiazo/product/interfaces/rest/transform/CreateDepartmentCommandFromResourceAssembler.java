package com.cambiazo.product.interfaces.rest.transform;

import com.cambiazo.product.domain.model.commands.CreateDepartmentCommand;
import com.cambiazo.product.interfaces.rest.resources.CreateDepartmentResource;

public class CreateDepartmentCommandFromResourceAssembler {
    public static CreateDepartmentCommand toCommandFromResource(CreateDepartmentResource resource) {
        return new CreateDepartmentCommand(resource.name(), resource.countryId());
    }
}
