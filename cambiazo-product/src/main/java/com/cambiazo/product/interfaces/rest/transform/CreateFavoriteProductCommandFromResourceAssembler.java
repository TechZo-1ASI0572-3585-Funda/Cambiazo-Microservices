package com.cambiazo.product.interfaces.rest.transform;

import com.cambiazo.product.domain.model.commands.CreateFavoriteProductCommand;
import com.cambiazo.product.interfaces.rest.resources.CreateFavoriteProductResource;

public class CreateFavoriteProductCommandFromResourceAssembler {
    public static CreateFavoriteProductCommand toCommandFromResource(CreateFavoriteProductResource resource) {
        return new CreateFavoriteProductCommand(resource.productId(), resource.userId());
    }
}
