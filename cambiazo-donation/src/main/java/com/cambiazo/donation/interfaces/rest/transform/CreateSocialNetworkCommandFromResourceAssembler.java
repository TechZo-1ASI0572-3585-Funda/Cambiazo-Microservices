package com.cambiazo.donation.interfaces.rest.transform;

import com.cambiazo.donation.domain.model.commands.CreateSocialNetworkCommand;
import com.cambiazo.donation.interfaces.rest.resources.CreateSocialNetworkResource;

public class CreateSocialNetworkCommandFromResourceAssembler {

    public static CreateSocialNetworkCommand toCommandFromResource(CreateSocialNetworkResource resource) {
        return new CreateSocialNetworkCommand(resource.name(), resource.url(), resource.ongId());
    }
}
