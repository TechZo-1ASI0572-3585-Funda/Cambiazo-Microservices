package com.cambiazo.product.interfaces.rest.transform;

import com.cambiazo.product.domain.model.entities.Department;
import com.cambiazo.product.interfaces.rest.resources.DepartmentResource;

public class DepartmentResourceFromEntityAssembler {
    public static DepartmentResource toResourceFromEntity(Department department) {
        return new DepartmentResource(department.getId(), department.getName(), department.getCountryId());
    }
}
