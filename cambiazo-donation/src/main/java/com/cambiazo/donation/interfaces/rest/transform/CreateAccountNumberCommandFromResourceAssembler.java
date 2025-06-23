package com.cambiazo.donation.interfaces.rest.transform;

import com.cambiazo.donation.domain.model.commands.CreateAccountNumberCommand;
import com.cambiazo.donation.interfaces.rest.resources.CreateAccountNumberResource;

public class CreateAccountNumberCommandFromResourceAssembler {

    public static CreateAccountNumberCommand toCommandFromResource(CreateAccountNumberResource resource) {
        return new CreateAccountNumberCommand(resource.name(), resource.cci(), resource.account(), resource.ongId());
    }
}
