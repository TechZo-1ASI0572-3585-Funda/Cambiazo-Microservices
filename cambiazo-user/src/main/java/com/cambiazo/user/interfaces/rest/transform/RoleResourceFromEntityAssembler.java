package com.cambiazo.user.interfaces.rest.transform;

import com.cambiazo.user.domain.model.entities.Role;
import com.cambiazo.user.interfaces.rest.resources.RoleResource;

public class RoleResourceFromEntityAssembler {
    public static RoleResource toResourceFromEntity(Role role) {
        return new RoleResource(role.getId(), role.getStringName());
    }
}
