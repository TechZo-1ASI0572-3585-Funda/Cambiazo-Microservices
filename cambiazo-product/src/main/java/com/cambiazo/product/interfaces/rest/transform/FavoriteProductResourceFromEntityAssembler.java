package com.cambiazo.product.interfaces.rest.transform;

import com.cambiazo.product.domain.model.entities.FavoriteProduct;
import com.cambiazo.product.interfaces.rest.resources.FavoriteProductResource;

public class FavoriteProductResourceFromEntityAssembler {
    public static FavoriteProductResource toResourceFromEntity(FavoriteProduct entity) {
        return new FavoriteProductResource(entity.getId(), entity.getProductId(), entity.getUserId());
    }
}
