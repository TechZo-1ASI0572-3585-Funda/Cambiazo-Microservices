package com.cambiazo.product.client;

import com.cambiazo.product.domain.model.dtos.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "cmbz-user")
public interface UserClient {

    @GetMapping("/api/v2/users/{userId}")
    ResponseEntity<UserDto> getUserById(@PathVariable Long userId);

    @GetMapping("/api/v2/users")
    ResponseEntity<List<UserDto>> getAllUsers();
}
