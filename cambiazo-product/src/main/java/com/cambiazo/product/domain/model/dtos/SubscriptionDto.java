package com.cambiazo.product.domain.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Builder
@AllArgsConstructor
public class SubscriptionDto {
    private Long id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String state;
    private Long userId;
    private PlanDto plan;
}
