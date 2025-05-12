package com.cambiazo.product.domain.services;

import com.cambiazo.product.domain.model.dtos.FavoriteProductDto;
import com.cambiazo.product.domain.model.queries.GetAllFavoriteProductsByUserIdQuery;
import com.cambiazo.product.http.response.GetUserByIdResponse;

import java.util.List;

public interface IFavoriteProductQueryService {

    List<FavoriteProductDto>handle(GetAllFavoriteProductsByUserIdQuery query);

//    GetUserByIdResponse findUserById(Long userId);
}
