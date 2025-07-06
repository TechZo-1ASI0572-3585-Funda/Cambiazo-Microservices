package com.cambiazo.product.client;

import com.cambiazo.product.domain.model.dtos.SubscriptionDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cmbz-user")
public interface SubscriptionClient {
    @GetMapping("/api/v2/subscriptions/active/{userId}")
    ResponseEntity<SubscriptionDto> getActiveSubscriptionByUserId(@PathVariable Long userId);
}
