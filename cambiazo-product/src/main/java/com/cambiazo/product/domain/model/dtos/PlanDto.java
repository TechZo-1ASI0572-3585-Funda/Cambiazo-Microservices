package com.cambiazo.product.domain.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlanDto {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private List<BenefitDto> benefits;
}
