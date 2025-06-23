package com.cambiazo.donation.interfaces.rest.transform;

import com.cambiazo.donation.domain.model.commands.CreateOngCommand;
import com.cambiazo.donation.interfaces.rest.resources.CreateOngResource;

public class CreateOngCommandFromResourceAssembler {
    public static CreateOngCommand toCommandFromResource(CreateOngResource resource){
        return new CreateOngCommand(
                resource.name(),
                resource.type(),
                resource.aboutUs(),
                resource.missionAndVision(),
                resource.supportForm(),
                resource.address(),
                resource.email(),
                resource.phone(),
                resource.logo(),
                resource.website(),
                resource.schedule(),
                resource.categoryOngId()
        );
    }
}
