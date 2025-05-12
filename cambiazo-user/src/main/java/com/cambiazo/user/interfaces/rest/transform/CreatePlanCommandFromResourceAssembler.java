package com.cambiazo.user.interfaces.rest.transform;

import com.cambiazo.user.domain.model.commands.CreatePlanCommand;
import com.cambiazo.user.interfaces.rest.resources.CreatePlanResource;

public class CreatePlanCommandFromResourceAssembler {
    public static CreatePlanCommand toCommandFromResource(CreatePlanResource resource) {
        return new CreatePlanCommand(resource.name(), resource.description(), resource.price());
    }
}
