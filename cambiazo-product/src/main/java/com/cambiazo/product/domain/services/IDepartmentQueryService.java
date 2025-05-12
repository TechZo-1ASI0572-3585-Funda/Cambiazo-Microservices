package com.cambiazo.product.domain.services;

import com.cambiazo.product.domain.model.entities.Department;
import com.cambiazo.product.domain.model.queries.GetAllDepartmentsQuery;
import com.cambiazo.product.domain.model.queries.GetDepartmentByIdQuery;

import java.util.List;
import java.util.Optional;

public interface IDepartmentQueryService {

    Optional<Department>handle(GetDepartmentByIdQuery query);

    List<Department>handle(GetAllDepartmentsQuery query);
}
