package com.cambiazo.user.domain.model.commands;

public record UpdateUserPasswordCommand(String username, String newPassword) {
}
