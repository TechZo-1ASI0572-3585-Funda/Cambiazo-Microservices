package com.cambiazo.user.interfaces.rest.transform;

import com.cambiazo.user.domain.model.entities.Subscription;
import com.cambiazo.user.interfaces.rest.resources.SubscriptionResource;

public class SubscriptionResourceFromEntityAssembler {
    public static SubscriptionResource toResourceFromEntity(Subscription entity) {
        return new SubscriptionResource(entity.getId(),entity.getState(),entity.getPlanId(),entity.getUserId(), entity.getStartDate(), entity.getEndDate());
    }
}
