package com.cambiazo.product.client;

import com.cambiazo.product.domain.model.dtos.SubscriptionDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="cmbz-user", url = "http://localhost:8090/api/v2/subscriptions")
public interface SubscriptionClient{
    @GetMapping("/active/{userId}")
    public ResponseEntity<SubscriptionDto>getActiveSubscriptionByUserId(@PathVariable Long userId);
}
