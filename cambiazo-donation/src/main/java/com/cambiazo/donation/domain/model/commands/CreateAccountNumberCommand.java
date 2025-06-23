package com.cambiazo.donation.domain.model.commands;

public record CreateAccountNumberCommand(String name, String cci, String account, Long ongId) {
}
