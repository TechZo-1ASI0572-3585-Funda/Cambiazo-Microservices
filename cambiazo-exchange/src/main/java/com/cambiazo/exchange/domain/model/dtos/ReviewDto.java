package com.cambiazo.exchange.domain.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReviewDto {
    private Long id;
    private String message;
    private Integer rating;
    private String state;
    private Long exchangeId;
    private UserDto userAuthor;
    private UserDto userReceptor;
}
