package com.cambiazo.product.domain.model.commands;

public record CreateProductCategoryCommand(String name) {
    public CreateProductCategoryCommand {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name cannot be null or empty");
        }
    }
}
