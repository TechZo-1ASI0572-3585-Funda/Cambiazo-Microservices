package com.cambiazo.donation.infrastructure.persistence.jpa;

import com.cambiazo.donation.domain.model.entities.CategoryOng;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryOngRepository extends JpaRepository<CategoryOng, Long>{
    boolean existsByName(String name);
    boolean existsById(Long Id);

    boolean existsByNameAndIdIsNot(String name, Long id);
}
