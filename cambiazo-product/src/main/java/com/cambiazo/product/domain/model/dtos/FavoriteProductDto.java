package com.cambiazo.product.domain.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FavoriteProductDto {
    private Long id;

    private ProductDto product;

    private Long userId;

    public FavoriteProductDto(){
    }

}
