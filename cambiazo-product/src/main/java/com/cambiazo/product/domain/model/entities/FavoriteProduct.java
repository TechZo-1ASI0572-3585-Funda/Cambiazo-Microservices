package com.cambiazo.product.domain.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class FavoriteProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id" ,nullable = false)
    private Product productId;

    @Column(name="user_id", nullable = false)
    private Long userId;

    public FavoriteProduct() {
    }

    public FavoriteProduct(Product product, Long userId) {
        this.productId = product;
        this.userId = userId;
    }

    public Long getProductId() {
        return productId.getId();
    }

}
