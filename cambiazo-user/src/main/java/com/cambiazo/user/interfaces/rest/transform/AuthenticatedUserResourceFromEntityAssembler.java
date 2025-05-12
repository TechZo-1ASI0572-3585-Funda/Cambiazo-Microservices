package com.cambiazo.user.interfaces.rest.transform;

import com.cambiazo.user.domain.model.aggregates.User;
import com.cambiazo.user.interfaces.rest.resources.AuthenticatedUserResource;

public class AuthenticatedUserResourceFromEntityAssembler {
    public static AuthenticatedUserResource toResourceFromEntity(User user) {
        return new AuthenticatedUserResource(
                user.getId(),
                user.getUsername(),
                user.getName(),
                user.getPhoneNumber(),
                user.getProfilePicture(),
                user.getIsActive(),
                user.getIsGoogleAccount() != null ? user.getIsGoogleAccount() : false
        );
    }
}
