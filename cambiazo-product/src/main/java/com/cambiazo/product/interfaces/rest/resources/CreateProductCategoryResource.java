package com.cambiazo.product.interfaces.rest.resources;

public record CreateProductCategoryResource(String name) {
    public CreateProductCategoryResource {
        if (name == null) {
            throw new IllegalArgumentException("name cannot be null");
        }
    }
}
