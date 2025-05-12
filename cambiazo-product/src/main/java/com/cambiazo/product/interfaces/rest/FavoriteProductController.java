package com.cambiazo.product.interfaces.rest;


import com.cambiazo.product.domain.model.dtos.FavoriteProductDto;
import com.cambiazo.product.domain.model.queries.GetAllFavoriteProductsByUserIdQuery;
import com.cambiazo.product.domain.services.IFavoriteProductCommandService;
import com.cambiazo.product.domain.services.IFavoriteProductQueryService;
import com.cambiazo.product.interfaces.rest.resources.CreateFavoriteProductResource;
import com.cambiazo.product.interfaces.rest.resources.FavoriteProductResource;
import com.cambiazo.product.interfaces.rest.transform.CreateFavoriteProductCommandFromResourceAssembler;
import com.cambiazo.product.interfaces.rest.transform.FavoriteProductResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v2/favorite-products")
@Tag(name="Favorite Products", description="Favorite Products Management Endpoints")
public class FavoriteProductController {
    private final IFavoriteProductCommandService favoriteProductService;

    private final IFavoriteProductQueryService favoriteProductQueryService;

    public FavoriteProductController(IFavoriteProductCommandService favoriteProductService, IFavoriteProductQueryService favoriteProductQueryService) {
        this.favoriteProductService = favoriteProductService;
        this.favoriteProductQueryService = favoriteProductQueryService;
    }

    @Operation(summary="Create a new Favorite Product", description="Create a new Favorite Product with the input data.")
    @PostMapping
    public ResponseEntity<FavoriteProductResource>createFavoriteProduct(@RequestBody CreateFavoriteProductResource resource){
        try {
            var createFavoriteProductCommand = CreateFavoriteProductCommandFromResourceAssembler.toCommandFromResource(resource);
            var favoriteProduct = favoriteProductService.handle(createFavoriteProductCommand);
            var favoriteProductResource = FavoriteProductResourceFromEntityAssembler.toResourceFromEntity(favoriteProduct.get());
            return ResponseEntity.status(CREATED).body(favoriteProductResource);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary="Get Favorite Products by User Id", description="Get Favorite Products by User Id with the input data.")
    @GetMapping("/{userId}")
    public ResponseEntity<List<FavoriteProductDto>>getFavoriteProductsByUserId(@PathVariable Long userId) {
        try {
            var getFavoriteProductsByUserIdQuery = new GetAllFavoriteProductsByUserIdQuery(userId);
            var favoriteProducts = favoriteProductQueryService.handle(getFavoriteProductsByUserIdQuery);
            return ResponseEntity.ok(favoriteProducts);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary="Delete a Favorite Product", description="Delete a Favorite Product with the input data.")
    @DeleteMapping("/delete/{userId}/{favoriteProductId}")
    public ResponseEntity<Void> deleteFavoriteProduct(@PathVariable Long userId, @PathVariable Long favoriteProductId) {
        try {
            favoriteProductService.handleDeleteFavoriteProductByUserIdAndProductId(userId,favoriteProductId);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @DeleteMapping("/delete/{favoriteProductId}")
    public ResponseEntity<Void> deleteFavoriteProductById(@PathVariable Long favoriteProductId) {
        try {
            favoriteProductService.handleDeleteFavoriteProductById(favoriteProductId);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
