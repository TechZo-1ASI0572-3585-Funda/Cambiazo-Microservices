package com.cambiazo.product.client;

import com.cambiazo.product.domain.model.dtos.PlanDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cmbz-user")
public interface PlanClient {
    @GetMapping("/api/v2/plans/{id}")
    ResponseEntity<PlanDto> getPlanById(@PathVariable Long id);
}
