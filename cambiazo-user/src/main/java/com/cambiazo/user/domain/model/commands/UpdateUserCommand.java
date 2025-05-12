package com.cambiazo.user.domain.model.commands;

public record UpdateUserCommand(Long userId,String username, String password, String name, String phoneNumber, String profilePicture, Boolean isActive) {
}
