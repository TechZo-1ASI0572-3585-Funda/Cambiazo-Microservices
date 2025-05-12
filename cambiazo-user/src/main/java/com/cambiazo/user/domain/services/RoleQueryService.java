package com.cambiazo.user.domain.services;

import com.cambiazo.user.domain.model.entities.Role;
import com.cambiazo.user.domain.model.queries.GetAllRolesQuery;
import com.cambiazo.user.domain.model.queries.GetRoleByNameQuery;

import java.util.List;
import java.util.Optional;

public interface RoleQueryService {
    List<Role> handle(GetAllRolesQuery query);
    Optional<Role> handle(GetRoleByNameQuery query);
}
