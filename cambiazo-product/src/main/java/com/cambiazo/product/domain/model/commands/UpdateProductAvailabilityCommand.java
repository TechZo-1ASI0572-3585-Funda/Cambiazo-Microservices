package com.cambiazo.product.domain.model.commands;

public record UpdateProductAvailabilityCommand(
        Long id,
        Boolean available
) {
}
