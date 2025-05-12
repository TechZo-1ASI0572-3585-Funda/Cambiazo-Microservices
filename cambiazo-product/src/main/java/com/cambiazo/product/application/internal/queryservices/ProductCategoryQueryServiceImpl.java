package com.cambiazo.product.application.internal.queryservices;

import com.cambiazo.product.domain.model.entities.ProductCategory;
import com.cambiazo.product.domain.model.queries.GetAllProductCategoriesQuery;
import com.cambiazo.product.domain.model.queries.GetProductCategoryByIdQuery;
import com.cambiazo.product.domain.services.IProductCategoryQueryService;
import com.cambiazo.product.infrastructure.persistence.jpa.IProductCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductCategoryQueryServiceImpl implements IProductCategoryQueryService {

    private final IProductCategoryRepository productCategoryRepository;

    public ProductCategoryQueryServiceImpl(IProductCategoryRepository productCategoryRepository){
        this.productCategoryRepository=productCategoryRepository;
    }

    @Override
    public Optional<ProductCategory>handle(GetProductCategoryByIdQuery query){
        return this.productCategoryRepository.findById(query.id());
    }

    @Override
    public List<ProductCategory>handle(GetAllProductCategoriesQuery query){
        return this.productCategoryRepository.findAll();
    }
}
