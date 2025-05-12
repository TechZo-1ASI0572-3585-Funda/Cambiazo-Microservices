package com.cambiazo.user.interfaces.rest.transform;

import com.cambiazo.user.domain.model.commands.SignInCommand;
import com.cambiazo.user.interfaces.rest.resources.SignInResource;

public class SignInCommandFromResourceAssembler {
    public static SignInCommand toCommandFromResource(SignInResource signInResource) {
        return new SignInCommand(
                signInResource.username(),
                signInResource.password()
        );
    }
}