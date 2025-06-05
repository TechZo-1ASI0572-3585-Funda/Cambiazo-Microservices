package com.cambiazo.exchange.interfaces.rest.transform;


import com.cambiazo.exchange.domain.model.entities.Review;
import com.cambiazo.exchange.interfaces.rest.resources.ReviewResource;

public class ReviewResourceFromEntityAssembler {
    public static ReviewResource toResourceFromEntity(Review entity) {
        return new ReviewResource(entity.getId(), entity.getMessage(), entity.getRating(), entity.getState(), entity.getExchangeId().getId(), entity.getUserAuthorId(), entity.getUserReceptorId());
    }
}
