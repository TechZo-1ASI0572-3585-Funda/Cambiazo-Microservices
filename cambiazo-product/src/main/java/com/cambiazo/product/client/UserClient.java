package com.cambiazo.product.client;

import com.cambiazo.product.domain.model.dtos.SubscriptionDto;
import com.cambiazo.product.domain.model.dtos.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="cmbz-user", url = "http://localhost:8090/api/v2/users")
public interface UserClient {

    @GetMapping(value = "/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long userId);


    @GetMapping(value = "")
    public ResponseEntity<List<UserDto>> getAllUsers();
}

