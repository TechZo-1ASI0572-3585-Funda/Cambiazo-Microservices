package com.cambiazo.exchange.interfaces.rest.transform;


import com.cambiazo.exchange.domain.model.commands.UpdateExchangeStatusCommand;
import com.cambiazo.exchange.interfaces.rest.resources.UpdateExchangeStatusResource;

public class UpdateExchangeStatusCommandFromResourceAssembler {

            public static UpdateExchangeStatusCommand toCommandFromResource(Long exchangeId, UpdateExchangeStatusResource resource) {
                return new UpdateExchangeStatusCommand(exchangeId, resource.status());
            }
}
