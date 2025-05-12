package com.cambiazo.product.domain.model.dtos;

import com.cambiazo.product.domain.model.entities.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private String desiredObject;
    private Double price;
    private String image;
    private Boolean boost;
    private Boolean available;
    private UserDto user;
    private ProductCategory productCategory;
    private Location location;
    private Date createdAt;

    public ProductDto(Product product, UserDto user, ProductCategory productCategory, Location location) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.desiredObject = product.getDesiredObject();
        this.price = product.getPrice();
        this.image = product.getImage();
        this.boost = product.getBoost();
        this.available = product.getAvailable();
        this.user = user;
        this.productCategory = productCategory;
        this.location = location;
        this.createdAt = product.getCreatedAt();
    }
}

