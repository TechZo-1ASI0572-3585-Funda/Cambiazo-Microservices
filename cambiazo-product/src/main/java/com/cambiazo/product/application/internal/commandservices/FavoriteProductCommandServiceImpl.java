package com.cambiazo.product.application.internal.commandservices;

import com.cambiazo.product.domain.model.commands.CreateFavoriteProductCommand;
import com.cambiazo.product.domain.model.entities.FavoriteProduct;
import com.cambiazo.product.domain.model.entities.Product;
import com.cambiazo.product.domain.services.IFavoriteProductCommandService;
import com.cambiazo.product.infrastructure.persistence.jpa.IFavoriteProductRepository;
import com.cambiazo.product.infrastructure.persistence.jpa.IProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FavoriteProductCommandServiceImpl implements IFavoriteProductCommandService {

    private final IFavoriteProductRepository favoriteProductRepository;


    private final IProductRepository productRepository;

    public FavoriteProductCommandServiceImpl(IFavoriteProductRepository favoriteProductRepository, IProductRepository productRepository) {
        this.favoriteProductRepository = favoriteProductRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Optional<FavoriteProduct>handle(CreateFavoriteProductCommand command) {

        Product product = productRepository.findById(command.productId()).orElseThrow(()-> new IllegalArgumentException("Product not found"));

        if(favoriteProductRepository.existsByUserIdAndProductId(command.userId(),product)){
            throw new IllegalArgumentException("Favorite Product already exists");
        }
        var favoriteProduct = new FavoriteProduct(product,command.userId());
        favoriteProductRepository.save(favoriteProduct);
        return Optional.of(favoriteProduct);
    }

    @Override
    public boolean handleDeleteFavoriteProductByUserIdAndProductId(Long userId, Long productId) {

        Product product = productRepository.findById(productId).orElseThrow(()-> new IllegalArgumentException("Product not found"));

        Optional<FavoriteProduct>favoriteProduct = favoriteProductRepository.findFavoriteProductByUserIdAndProductId(userId,product);
        if(favoriteProduct.isPresent()){
            favoriteProductRepository.delete(favoriteProduct.get());
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean handleDeleteFavoriteProductById(Long id) {
        if(favoriteProductRepository.existsFavoriteProductById(id)){
            favoriteProductRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }
}
