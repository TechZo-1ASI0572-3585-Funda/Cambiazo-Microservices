package com.cambiazo.user.domain.model.dto;

import com.cambiazo.user.domain.model.entities.Benefit;
import com.cambiazo.user.domain.model.entities.Plan;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PlanDto {
    private Long id;

    private String name;

    private String description;

    private Double price;

    List<Benefit>benefits;

    public PlanDto(Plan plan, List<Benefit>benefits){
        this.id = plan.getId();
        this.name = plan.getName();
        this.description = plan.getDescription();
        this.price = plan.getPrice();
        this.benefits = benefits;
    }
}
