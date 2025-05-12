package com.cambiazo.product.interfaces.rest.transform;

import com.cambiazo.product.domain.model.entities.ProductCategory;
import com.cambiazo.product.interfaces.rest.resources.ProductCategoryResource;

public class ProductCategoryResourceFromEntityAssembler {
    public static ProductCategoryResource toResourceFromEntity(ProductCategory entity) {
        return new ProductCategoryResource(entity.getId(), entity.getName());
    }
}
