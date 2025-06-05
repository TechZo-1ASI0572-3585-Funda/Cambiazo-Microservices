package com.cambiazo.exchange.domain.model.dtos;

public record UpdateProductAvailabilityResource(
        Long productId,
        Boolean available
) {
}