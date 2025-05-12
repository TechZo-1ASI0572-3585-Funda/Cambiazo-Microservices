package com.cambiazo.user.interfaces.rest.transform;

import com.cambiazo.user.domain.model.commands.CreateBenefitCommand;
import com.cambiazo.user.interfaces.rest.resources.CreateBenefitResource;


public class CreateBenefitCommandFromResourceAssembler {

    public static CreateBenefitCommand toCommandFromResource(CreateBenefitResource resource) {
        return new CreateBenefitCommand(resource.description(), resource.planId());
    }
}
