package com.cambiazo.exchange.interfaces.rest.transform;


import com.cambiazo.exchange.domain.model.commands.CreateReviewCommand;
import com.cambiazo.exchange.interfaces.rest.resources.CreateReviewResource;

public class CreateReviewCommandFromResourceAssembler {
    public static CreateReviewCommand toCommandFromResource(CreateReviewResource resource) {
        return new CreateReviewCommand(resource.message(), resource.rating(), resource.state(), resource.exchangeId(), resource.userAuthorId(), resource.userReceptorId());
    }
}
