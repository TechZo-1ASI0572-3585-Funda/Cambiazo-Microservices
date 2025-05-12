package com.cambiazo.product.interfaces.rest.transform;

import com.cambiazo.product.domain.model.entities.Product;
import com.cambiazo.product.interfaces.rest.resources.ProductResource;

public class ProductResourceFromEntityAssembler {

    public static ProductResource toResourceFromEntity(Product entity) {
        return new ProductResource(entity.getId(), entity.getName(), entity.getDescription(), entity.getDesiredObject(), entity.getPrice(), entity.getImage(), entity.getBoost(), entity.getAvailable(), entity.getProductCategoryId(), entity.getUserId(), entity.getDistrictId());
    }
}
