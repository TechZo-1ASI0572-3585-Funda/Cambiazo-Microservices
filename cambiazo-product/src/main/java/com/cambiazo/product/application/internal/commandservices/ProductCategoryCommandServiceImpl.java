package com.cambiazo.product.application.internal.commandservices;

import com.cambiazo.product.domain.model.commands.CreateProductCategoryCommand;
import com.cambiazo.product.domain.model.entities.ProductCategory;
import com.cambiazo.product.domain.services.IProductCategoryCommandService;
import com.cambiazo.product.infrastructure.persistence.jpa.IProductCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductCategoryCommandServiceImpl implements IProductCategoryCommandService {
    private final IProductCategoryRepository productCategoryRepository;

    public ProductCategoryCommandServiceImpl(IProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }

    @Override
    public Optional<ProductCategory> handle(CreateProductCategoryCommand command) {
        if (productCategoryRepository.existsByName(command.name())) {
            throw new IllegalArgumentException("Product category with same name already exists");
        }
        var productCategory = new ProductCategory(command);
        var createdProductCategory = productCategoryRepository.save(productCategory);
        return Optional.of(createdProductCategory);
    }
}
