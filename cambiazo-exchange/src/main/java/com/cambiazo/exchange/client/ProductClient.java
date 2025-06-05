package com.cambiazo.exchange.client;

import com.cambiazo.exchange.domain.model.dtos.ProductDto;
import com.cambiazo.exchange.domain.model.dtos.UpdateProductAvailabilityResource;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name="cmbz-product", url = "http://localhost:9090/api/v2/products")
public interface ProductClient {

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id);

    @GetMapping()
    public ResponseEntity<List<ProductDto>> getAllProducts();

    @PostMapping("/edit/available")
    public ResponseEntity<Boolean> updateProductAvailability(@RequestBody UpdateProductAvailabilityResource resource);
}
