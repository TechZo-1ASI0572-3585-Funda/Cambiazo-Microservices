package com.cambiazo.product.interfaces.rest.transform;

import com.cambiazo.product.domain.model.commands.UpdateProductAvailabilityCommand;
import com.cambiazo.product.domain.model.commands.UpdateProductCommand;
import com.cambiazo.product.interfaces.rest.resources.UpdateProductAvailabilityResource;
import com.cambiazo.product.interfaces.rest.resources.UpdateProductResource;

public class UpdateProductAvailabilityCommandFromResourceAssembler {
    public static UpdateProductAvailabilityCommand toCommandFromResource(UpdateProductAvailabilityResource resource) {
        return new UpdateProductAvailabilityCommand(
                resource.productId(),
                resource.available()
        );
    }
}
