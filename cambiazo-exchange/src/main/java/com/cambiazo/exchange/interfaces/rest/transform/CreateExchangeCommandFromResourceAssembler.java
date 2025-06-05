package com.cambiazo.exchange.interfaces.rest.transform;


import com.cambiazo.exchange.domain.model.commands.CreateExchangeCommand;
import com.cambiazo.exchange.interfaces.rest.resources.CreateExchangeResource;

public class CreateExchangeCommandFromResourceAssembler {
    public static CreateExchangeCommand toCommandFromResource(CreateExchangeResource resource) {
        return new CreateExchangeCommand(resource.productOwnId(), resource.productChangeId(), resource.status());
    }
}
