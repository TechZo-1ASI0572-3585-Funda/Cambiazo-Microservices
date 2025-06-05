package com.cambiazo.product.interfaces.rest.resources;

public record UpdateProductAvailabilityResource(
        Long productId,
        Boolean available
) {
}
