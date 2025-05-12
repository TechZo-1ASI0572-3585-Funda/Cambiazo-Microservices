package com.cambiazo.user.interfaces.rest.transform;

import com.cambiazo.user.domain.model.commands.UpdateProfileUserCommand;
import com.cambiazo.user.interfaces.rest.resources.UpdateUserProfileResource;

public class UpdateProfileUserCommandFromResourceAssembler {
    public static UpdateProfileUserCommand toCommandFromResource(Long userId, UpdateUserProfileResource resource) {
        return new UpdateProfileUserCommand(userId,resource.username(), resource.name(), resource.phoneNumber(), resource.profilePicture());
    }
}
