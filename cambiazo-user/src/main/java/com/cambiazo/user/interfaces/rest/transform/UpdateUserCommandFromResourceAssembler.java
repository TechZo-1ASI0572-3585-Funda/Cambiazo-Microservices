package com.cambiazo.user.interfaces.rest.transform;

import com.cambiazo.user.domain.model.commands.UpdateUserCommand;
import com.cambiazo.user.interfaces.rest.resources.UpdateUserResource;

public class UpdateUserCommandFromResourceAssembler {
    public static UpdateUserCommand toCommandFromResource(Long userId,UpdateUserResource resource) {
        return new UpdateUserCommand(userId,resource.username(), resource.password(), resource.name(), resource.phoneNumber(), resource.profilePicture(), resource.isActive());
    }
}
