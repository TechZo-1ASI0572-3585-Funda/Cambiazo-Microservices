package com.cambiazo.exchange.domain.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ProductCategoryDto {
    private Long id;
    private String name;
}
