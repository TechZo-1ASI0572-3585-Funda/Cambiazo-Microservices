package com.cambiazo.product.infrastructure.persistence.jpa;

import com.cambiazo.product.domain.model.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICountryRepository extends JpaRepository<Country, Long> {
    boolean existsByName(String name);
}
