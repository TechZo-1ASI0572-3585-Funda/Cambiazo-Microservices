package com.cambiazo.product.client;

import com.cambiazo.product.domain.model.dtos.PlanDto;
import com.cambiazo.product.domain.model.dtos.SubscriptionDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="cmbz-user", url = "http://localhost:8090/api/v2/plans")
public interface PlanClient{
    @GetMapping("/{id}")
    public ResponseEntity<PlanDto>getPlanById(@PathVariable Long id);
}
