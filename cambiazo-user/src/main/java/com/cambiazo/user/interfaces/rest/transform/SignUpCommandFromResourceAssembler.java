package com.cambiazo.user.interfaces.rest.transform;

import com.cambiazo.user.domain.model.commands.SignUpCommand;
import com.cambiazo.user.domain.model.entities.Role;
import com.cambiazo.user.interfaces.rest.resources.SignUpResource;

import java.util.ArrayList;

public class SignUpCommandFromResourceAssembler {
    public static SignUpCommand toCommandFromResource(SignUpResource resource) {
        var roles = resource.roles() != null ? resource.roles().stream().map(Role::toRoleFromName).toList() : new ArrayList<Role>();
        return new SignUpCommand(
                resource.username(),
                resource.password(),
                resource.name(),
                resource.phoneNumber(),
                resource.profilePicture(),
                resource.isGoogleAccount() != null ? resource.isGoogleAccount() : false,
                roles
        );
    }
}