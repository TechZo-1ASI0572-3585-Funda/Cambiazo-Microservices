package com.cambiazo.exchange.domain.model.dtos;

import java.util.Date;
import java.util.List;

public record UserDto(
    Long id,
    String username,
    String name,
    String phoneNumber,
    String profilePicture,
    Date createdAt,
    Boolean isActive,
    Boolean isGoogleAccount,
    List<String> roles
) {
    public UserDto {
        if (isGoogleAccount == null) {
            isGoogleAccount = false;
        }
    }
}
