package com.cambiazo.product.interfaces.rest.transform;

import com.cambiazo.product.domain.model.commands.CreateProductCategoryCommand;
import com.cambiazo.product.interfaces.rest.resources.CreateProductCategoryResource;

public class CreateProductCategoryCommandFromResourceAssembler {
    public static CreateProductCategoryCommand toCommandFromResource(CreateProductCategoryResource resource) {
        return new CreateProductCategoryCommand(resource.name());
    }
}
