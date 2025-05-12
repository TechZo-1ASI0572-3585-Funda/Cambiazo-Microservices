package com.cambiazo.product.interfaces.rest.transform;

import com.cambiazo.product.domain.model.commands.UpdateProductCommand;
import com.cambiazo.product.interfaces.rest.resources.UpdateProductResource;

public class UpdateProductCommandFromResourceAssembler {
    public static UpdateProductCommand toCommandFromResource(Long productId, UpdateProductResource resource) {
        return new UpdateProductCommand(
                productId,
                resource.name(),
                resource.description(),
                resource.desiredObject(),
                resource.price(),
                resource.image(),
                resource.boost(),
                resource.available(),
                resource.productCategoryId(),
                resource.userId(),
                resource.districtId()
        );
    }
}
