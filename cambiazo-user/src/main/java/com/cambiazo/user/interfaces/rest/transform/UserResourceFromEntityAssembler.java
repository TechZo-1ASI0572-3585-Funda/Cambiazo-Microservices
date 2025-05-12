package com.cambiazo.user.interfaces.rest.transform;

import com.cambiazo.user.domain.model.aggregates.User;
import com.cambiazo.user.domain.model.entities.Role;
import com.cambiazo.user.interfaces.rest.resources.UserResource;

public class UserResourceFromEntityAssembler {
    public static UserResource toResourceFromEntity(User user) {
        var roles = user.getRoles().stream().map(Role::getStringName).toList();
        return new UserResource(
                user.getId(),
                user.getUsername(),
                user.getName(),
                user.getPhoneNumber(),
                user.getProfilePicture(),
                user.getIsActive(),
                user.getIsGoogleAccount() != null ? user.getIsGoogleAccount() : false,
                roles
        );
    }
}