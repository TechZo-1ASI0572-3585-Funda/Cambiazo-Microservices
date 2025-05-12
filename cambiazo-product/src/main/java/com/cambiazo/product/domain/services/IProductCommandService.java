package com.cambiazo.product.domain.services;

import com.cambiazo.product.domain.model.commands.CreateProductCommand;
import com.cambiazo.product.domain.model.commands.DeleteProductOfPendingExchangesCommand;
import com.cambiazo.product.domain.model.commands.UpdateProductCommand;
import com.cambiazo.product.domain.model.entities.Product;

import java.util.Optional;

public interface IProductCommandService {
    Optional<Product>handle(CreateProductCommand command);

    Optional<Product>handle(UpdateProductCommand command);
//    boolean handleDeleteProduct(DeleteProductOfPendingExchangesCommand command);
}
