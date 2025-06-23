package com.cambiazo.donation.interfaces.rest.transform;


import com.cambiazo.donation.domain.model.commands.CreateProjectCommand;
import com.cambiazo.donation.interfaces.rest.resources.CreateProjectResource;

public class CreateProjectCommandFromResourceAssembler {
    public static CreateProjectCommand toCommandFromResource(CreateProjectResource resource) {
        return new CreateProjectCommand(resource.name(), resource.description(), resource.ongId());
    }
}
