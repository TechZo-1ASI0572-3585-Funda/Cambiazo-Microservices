package com.cambiazo.user.domain.model.commands;

public record UpdateProfileUserCommand(Long userId, String username, String name, String phoneNumber, String profilePicture) {
}
