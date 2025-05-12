package com.cambiazo.product.domain.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class UserDto{

    private Long id;
    private String username;
    private String name;
    private String phoneNumber;
    private String profilePicture;
    private Boolean isActive;
    private Boolean isGoogleAccount;
    private List<String> roles;

    public UserDto() {
        if (isGoogleAccount == null) {
            isGoogleAccount = false;
        }
    }
}