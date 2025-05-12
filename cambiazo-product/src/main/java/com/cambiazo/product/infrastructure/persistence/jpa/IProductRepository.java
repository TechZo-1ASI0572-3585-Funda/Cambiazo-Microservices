package com.cambiazo.product.infrastructure.persistence.jpa;


import com.cambiazo.product.domain.model.entities.Product;
import com.cambiazo.product.domain.model.entities.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
    boolean existsByNameAndId(String name, Long id);

    List<Product>findProductsByUserId(Long userId);

    List<Product>findProductsByProductCategoryId(ProductCategory productCategoryId);

    @Query("SELECT COUNT(p) FROM Product p WHERE p.userId = :userId AND p.createdAt > :createdAt")
    Long countByUserIdAndCreatedAtAfter(Long userId, Date createdAt);

    @Query("SELECT COUNT(p) FROM Product p WHERE p.userId = :userId AND p.createdAt > :createdAt AND p.boost=true")
    Long countBoostsByUserIdAndCreatedAtAfter(Long userId, Date createdAt);

    @Modifying
    @Query("UPDATE Product p SET p.available = false WHERE p.userId = :userId")
    void updateProductAvailabilityByUser(@Param("user") Long userId);
}
