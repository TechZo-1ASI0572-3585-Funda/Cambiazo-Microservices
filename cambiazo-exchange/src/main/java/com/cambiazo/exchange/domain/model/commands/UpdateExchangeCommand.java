package com.cambiazo.exchange.domain.model.commands;

public record UpdateExchangeCommand(
    Long id,
    Long productOwnId,
    Long productChangeId,
    String status
) {
}
