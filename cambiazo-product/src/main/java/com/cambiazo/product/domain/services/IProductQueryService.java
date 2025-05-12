package com.cambiazo.product.domain.services;

import com.cambiazo.product.domain.model.dtos.ProductDto;
import com.cambiazo.product.domain.model.queries.GetAllProductsByProductCategoryIdQuery;
import com.cambiazo.product.domain.model.queries.GetAllProductsByUserIdQuery;
import com.cambiazo.product.domain.model.queries.GetAllProductsQuery;
import com.cambiazo.product.domain.model.queries.GetProductByIdQuery;

import java.util.List;
import java.util.Optional;

public interface IProductQueryService {

    Optional<ProductDto>handle(GetProductByIdQuery query);

    List<ProductDto>handle(GetAllProductsQuery query);

    List<ProductDto>handle(GetAllProductsByUserIdQuery query);

    List<ProductDto>handle(GetAllProductsByProductCategoryIdQuery query);
}
