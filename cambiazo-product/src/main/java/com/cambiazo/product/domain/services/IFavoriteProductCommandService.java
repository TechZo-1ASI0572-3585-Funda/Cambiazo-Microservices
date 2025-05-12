package com.cambiazo.product.domain.services;

import com.cambiazo.product.domain.model.commands.CreateFavoriteProductCommand;
import com.cambiazo.product.domain.model.entities.FavoriteProduct;

import java.util.Optional;

public interface IFavoriteProductCommandService {
    Optional<FavoriteProduct> handle(CreateFavoriteProductCommand command);

    boolean handleDeleteFavoriteProductByUserIdAndProductId(Long userId, Long productId);

    boolean handleDeleteFavoriteProductById(Long id);
}
