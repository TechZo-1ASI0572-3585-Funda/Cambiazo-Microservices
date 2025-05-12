package com.cambiazo.user.domain.model.dto;

import com.cambiazo.user.domain.model.entities.Subscription;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
public class SubscriptionDto {

    private Long id;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private String state;

    private Long userId;

    private PlanDto plan;

    public SubscriptionDto(){
    }

    public SubscriptionDto(Subscription subscription,PlanDto plan){
        this.id = subscription.getId();
        this.startDate = subscription.getStartDate();
        this.endDate = subscription.getEndDate();
        this.state = subscription.getState();
        this.userId = subscription.getUserId();
        this.plan = plan;
    }
}
