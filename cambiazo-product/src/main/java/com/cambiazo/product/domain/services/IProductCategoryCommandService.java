package com.cambiazo.product.domain.services;

import com.cambiazo.product.domain.model.commands.CreateProductCategoryCommand;
import com.cambiazo.product.domain.model.entities.ProductCategory;

import java.util.Optional;

public interface IProductCategoryCommandService {
    Optional<ProductCategory>handle(CreateProductCategoryCommand command);
}
