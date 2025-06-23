package com.cambiazo.donation.interfaces.rest.transform;

import com.cambiazo.donation.domain.model.aggregates.SocialNetwork;
import com.cambiazo.donation.interfaces.rest.resources.SocialNetworkResource;

public class SocialNetworkResourceFromEntityAssembler {
    public static SocialNetworkResource toResourceFromEntity(SocialNetwork entity) {
        return new SocialNetworkResource(entity.getId(), entity.getName(), entity.getUrl(), entity.getOngId());
    }
}
