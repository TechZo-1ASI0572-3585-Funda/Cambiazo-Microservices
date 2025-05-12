package com.cambiazo.user.interfaces.rest.transform;

import com.cambiazo.user.domain.model.aggregates.User;
import com.cambiazo.user.domain.model.entities.Role;
import com.cambiazo.user.interfaces.rest.resources.UserResource2;

public class UserResource2FromEntityAssembler {
    public static UserResource2 toResourceFromEntity(User user) {
        var roles = user.getRoles().stream().map(Role::getStringName).toList();
        return new UserResource2(
                user.getId(),
                user.getUsername(),
                user.getName(),
                user.getPhoneNumber(),
                user.getProfilePicture(),
                user.getCreatedAt(),
                user.getIsActive(),
                user.getIsGoogleAccount() != null ? user.getIsGoogleAccount() : false,
                roles
        );
    }
}
