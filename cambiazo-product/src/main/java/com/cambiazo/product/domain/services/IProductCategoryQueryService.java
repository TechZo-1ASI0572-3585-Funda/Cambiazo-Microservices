package com.cambiazo.product.domain.services;

import com.cambiazo.product.domain.model.entities.ProductCategory;
import com.cambiazo.product.domain.model.queries.GetAllProductCategoriesQuery;
import com.cambiazo.product.domain.model.queries.GetProductCategoryByIdQuery;

import java.util.List;
import java.util.Optional;

public interface IProductCategoryQueryService {

    Optional<ProductCategory>handle(GetProductCategoryByIdQuery query);

    List<ProductCategory>handle(GetAllProductCategoriesQuery query);
}
