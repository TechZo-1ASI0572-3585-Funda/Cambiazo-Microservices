package com.cambiazo.product.application.internal.queryservices;

import com.cambiazo.product.domain.model.entities.Department;
import com.cambiazo.product.domain.model.queries.GetAllDepartmentsQuery;
import com.cambiazo.product.domain.model.queries.GetDepartmentByIdQuery;
import com.cambiazo.product.domain.services.IDepartmentQueryService;
import com.cambiazo.product.infrastructure.persistence.jpa.IDepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentQueryServiceImpl implements IDepartmentQueryService {

    private final IDepartmentRepository departmentRepository;

    public DepartmentQueryServiceImpl(IDepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Optional<Department>handle(GetDepartmentByIdQuery query) {
        return departmentRepository.findById(query.id());
    }

    public List<Department>handle(GetAllDepartmentsQuery query) {
        return departmentRepository.findAll();
    }
}
