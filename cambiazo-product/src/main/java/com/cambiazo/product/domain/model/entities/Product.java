package com.cambiazo.product.domain.model.entities;


import com.cambiazo.product.domain.model.commands.CreateProductCommand;
import com.cambiazo.product.shared.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Entity
public class Product extends AuditableAbstractAggregateRoot<Product> {

    @Column(nullable = false)
    @NotNull(message = "Name is required")
    private String name;

    @Column(nullable = false)
    @NotNull(message = "Description is required")
    private String description;

    @Column(nullable = false)
    @NotNull(message = "Desired Object is required")
    private String desiredObject;

    @Column(nullable = false)
    @NotNull(message = "Price is required")
    private Double price;

    @Column(nullable = false)
    @NotNull(message = "Image is required")
    private String image;

    @Column(nullable = false)
    @NotNull(message = "Boost is required")
    private Boolean boost;

    @Column(nullable = false)
    @NotNull(message = "Available is required")
    private Boolean available;


    @ManyToOne
    @JoinColumn(name = "product_category_id", nullable = false)
    private ProductCategory productCategoryId;


    @Column(name = "user_id", nullable = false)
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "district_id", nullable = false)
    private District districtId;

    public Product() {
    }

    public Product(CreateProductCommand command, ProductCategory productCategory, Long userId, District district){
        this.name = command.name();
        this.description = command.description();
        this.desiredObject = command.desiredObject();
        this.price = command.price();
        this.image = command.image();
        this.boost = command.boost();
        this.available = command.available();
        this.productCategoryId = productCategory;
        this.userId = userId;
        this.districtId = district;
    }

    public Product updateInformation(String name, String description, String desiredObject, Double price, String image, Boolean boost, Boolean available, ProductCategory productCategory, Long userId, District district) {
        this.name = name;
        this.description = description;
        this.desiredObject = desiredObject;
        this.price = price;
        this.image = image;
        this.boost = boost;
        this.available = available;
        this.productCategoryId = productCategory;
        this.userId = userId;
        this.districtId = district;
        return this;
    }

    public Long getDistrictId() {
        return districtId.getId();
    }

    public Long getProductCategoryId() {
        return productCategoryId.getId();
    }

}
