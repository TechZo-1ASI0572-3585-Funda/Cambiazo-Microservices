package com.cambiazo.user.interfaces.rest.transform;

import com.cambiazo.user.domain.model.commands.CreateSubscriptionCommand;
import com.cambiazo.user.interfaces.rest.resources.CreateSubscriptionResource;

public class CreateSubscriptionCommandFromResourceAssembler {
    public static CreateSubscriptionCommand toCommandFromResource(CreateSubscriptionResource resource) {
        return new CreateSubscriptionCommand(resource.state(), resource.planId(), resource.userId());
    }
}
