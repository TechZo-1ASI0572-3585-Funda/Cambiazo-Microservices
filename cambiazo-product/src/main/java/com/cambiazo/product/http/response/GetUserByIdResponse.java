package com.cambiazo.product.http.response;

import com.cambiazo.product.domain.model.dtos.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetUserByIdResponse {
    private UserDto user;
}
