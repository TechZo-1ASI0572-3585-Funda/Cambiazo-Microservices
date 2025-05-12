package com.cambiazo.user.interfaces.rest.transform;

import com.cambiazo.user.domain.model.commands.UpdateUserPasswordCommand;
import com.cambiazo.user.interfaces.rest.resources.UpdateUserPasswordResource;

public class UpdateUserPasswordCommandFromResourceAssembler {
    public static UpdateUserPasswordCommand toCommandFromResource(String username,UpdateUserPasswordResource resource) {
        return new UpdateUserPasswordCommand(username,resource.newPassword());
    }
}
