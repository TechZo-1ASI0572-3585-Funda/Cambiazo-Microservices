package com.cambiazo.user.interfaces.rest.resources;

public record AuthenticatedUserResource(
        Long id,
        String username,
        String name,
        String phoneNumber,
        String profilePicture,
        Boolean isActive,
        Boolean isGoogleAccount
) {
    public AuthenticatedUserResource {
        if (isGoogleAccount == null) {
            isGoogleAccount = false;
        }
    }
}