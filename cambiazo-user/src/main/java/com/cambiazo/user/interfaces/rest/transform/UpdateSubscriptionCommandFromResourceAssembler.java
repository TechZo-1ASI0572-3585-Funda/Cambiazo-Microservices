package com.cambiazo.user.interfaces.rest.transform;

import com.cambiazo.user.domain.model.commands.UpdateSubscriptionCommand;
import com.cambiazo.user.interfaces.rest.resources.UpdateSubscriptionResource;

public class UpdateSubscriptionCommandFromResourceAssembler {
    public static UpdateSubscriptionCommand toCommandFromResource(Long subscriptionId,UpdateSubscriptionResource resource) {
        return new UpdateSubscriptionCommand(subscriptionId, resource.state(), resource.planId(), resource.userId());
    }
}
