package com.cambiazo.user.domain.model.commands;

public record UpdateSubscriptionCommand(
        Long id,
        String state,
        Long planId,
        Long userId
) {
}
