package com.cambiazo.user.domain.model.commands;

import com.cambiazo.user.domain.model.entities.Role;

import java.util.List;

public record SignUpCommand(
        String username,
        String password,
        String name,
        String phoneNumber,
        String profilePicture,
        Boolean isGoogleAccount,
        List<Role> roles
) {
}
