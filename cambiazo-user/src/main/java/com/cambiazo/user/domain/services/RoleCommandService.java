package com.cambiazo.user.domain.services;

import com.cambiazo.user.domain.model.commands.SeedRolesCommand;

public interface RoleCommandService {
    void handle(SeedRolesCommand command);
}
