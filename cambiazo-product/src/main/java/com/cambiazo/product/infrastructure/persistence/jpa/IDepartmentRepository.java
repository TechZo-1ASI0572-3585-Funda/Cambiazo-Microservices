package com.cambiazo.product.infrastructure.persistence.jpa;

import com.cambiazo.product.domain.model.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDepartmentRepository extends JpaRepository<Department, Long> {
    boolean existsByName(String name);
}
