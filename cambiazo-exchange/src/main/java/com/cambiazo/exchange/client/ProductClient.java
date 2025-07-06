package com.cambiazo.exchange.client;

import com.cambiazo.exchange.domain.model.dtos.ProductDto;
import com.cambiazo.exchange.domain.model.dtos.UpdateProductAvailabilityResource;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "cmbz-product")
public interface ProductClient {

    @GetMapping("/api/v2/products/{id}")
    ResponseEntity<ProductDto> getProductById(@PathVariable Long id);

    @GetMapping("/api/v2/products")
    ResponseEntity<List<ProductDto>> getAllProducts();

    @PostMapping("/api/v2/products/edit/available")
    ResponseEntity<Boolean> updateProductAvailability(@RequestBody UpdateProductAvailabilityResource resource);
}
