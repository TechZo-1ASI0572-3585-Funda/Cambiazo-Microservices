package com.cambiazo.user.interfaces.rest.resources;

import java.util.List;

public record SignUpResource(
        String username,
        String password,
        String name,
        String phoneNumber,
        String profilePicture,
        Boolean isGoogleAccount,
        List<String> roles
) {
    public SignUpResource {
        if (isGoogleAccount == null) {
            isGoogleAccount = false;
        }
    }
}