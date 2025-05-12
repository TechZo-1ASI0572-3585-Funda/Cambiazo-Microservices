package com.cambiazo.product.domain.services;

import com.cambiazo.product.domain.model.commands.CreateDepartmentCommand;
import com.cambiazo.product.domain.model.entities.Department;

import java.util.Optional;

public interface IDepartmentCommandService {

    Optional<Department>handle(CreateDepartmentCommand command);
}
