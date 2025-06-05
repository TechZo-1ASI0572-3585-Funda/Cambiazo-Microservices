package com.cambiazo.exchange.domain.model.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class ProductDto {
    private Long id;
    private Date createdAt;
    private Date updatedAt;
    private String name;
    private String description;
    private String desiredObject;
    private Double price;
    private String image;
    private Boolean boost;
    private Boolean available;
    private ProductCategoryDto productCategory;
    private UserDto user;
    private Long districtId;
}
