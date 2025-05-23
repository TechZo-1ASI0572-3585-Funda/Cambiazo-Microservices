package com.cambiazo.product.interfaces.rest.resources;

public record CreateCountryResource(String name) {
    public CreateCountryResource {
        if (name == null) {
            throw new IllegalArgumentException("name cannot be null");
        }
    }
}
