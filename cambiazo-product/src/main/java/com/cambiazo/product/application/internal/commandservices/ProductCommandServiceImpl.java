package com.cambiazo.product.application.internal.commandservices;


import com.cambiazo.product.client.PlanClient;
import com.cambiazo.product.client.SubscriptionClient;
import com.cambiazo.product.client.UserClient;
import com.cambiazo.product.domain.model.commands.CreateProductCommand;
import com.cambiazo.product.domain.model.commands.DeleteProductOfPendingExchangesCommand;
import com.cambiazo.product.domain.model.commands.UpdateProductCommand;
import com.cambiazo.product.domain.model.dtos.PlanDto;
import com.cambiazo.product.domain.model.dtos.SubscriptionDto;
import com.cambiazo.product.domain.model.dtos.UserDto;
import com.cambiazo.product.domain.model.entities.*;
import com.cambiazo.product.domain.services.IProductCommandService;
import com.cambiazo.product.infrastructure.persistence.jpa.*;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductCommandServiceImpl implements IProductCommandService {

    private final IProductRepository productRepository;

    @Autowired
    private UserClient userClient;

    @Autowired
    private SubscriptionClient subscriptionClient;

    @Autowired
    private PlanClient planClient;

    private final IProductCategoryRepository productCategoryRepository;

    private final IDistrictRepository districtRepository;

    private final IFavoriteProductRepository favoriteProductRepository;


    public ProductCommandServiceImpl(IProductRepository productRepository, IProductCategoryRepository productCategoryRepository, IDistrictRepository districtRepository, IFavoriteProductRepository favoriteProductRepository) {
        this.productRepository = productRepository;
        this.productCategoryRepository = productCategoryRepository;
        this.districtRepository = districtRepository;
        this.favoriteProductRepository = favoriteProductRepository;
    }

    @Override
    public Optional<Product> handle(CreateProductCommand command) {
    try {
        ResponseEntity<UserDto> user = userClient.getUserById(command.userId());
        //imprime algo
        // 1. Imprimir todo el ResponseEntity
        System.out.println("ResponseEntity completo: " + user);

        // 2. Imprimir solo el cuerpo (body) del ResponseEntity
                System.out.println("Cuerpo de la respuesta: " + user.getBody());


        if (user.getBody() == null) {
            throw new IllegalArgumentException("User not found");
        }

        ResponseEntity<SubscriptionDto> subscriptionDto = subscriptionClient.getActiveSubscriptionByUserId(command.userId());

        System.out.println("ResponseEntity completo: " + subscriptionDto);

        // 2. Imprimir solo el cuerpo (body) del ResponseEntity
        System.out.println("Cuerpo de la respuesta: " + subscriptionDto.getBody());

        if (subscriptionDto.getBody() == null) {
            throw new IllegalArgumentException("Active subscription not found for user");
        }



//        Subscription subscription = subscriptionRepository.findSubscriptionActiveByUserId(command.userId())
//                .orElseThrow(() -> new IllegalArgumentException("Active subscription not found for user"));

        ResponseEntity<PlanDto> planDto = planClient.getPlanById(subscriptionDto.getBody().getPlan().getId());

        System.out.println("ResponseEntity completo: " + planDto);

        // 2. Imprimir solo el cuerpo (body) del ResponseEntity
        System.out.println("Cuerpo de la respuesta: " + planDto.getBody());

        // 4. Imprimir el código de estado HTTP
        System.out.println("Código de estado HTTP: " + planDto.getStatusCode());

        // 5. Imprimir los headers de la respuesta
        System.out.println("Headers: " + planDto.getHeaders());


        if (planDto.getBody() == null) {
            throw new IllegalArgumentException("Plan not found");
        }


//        Plan plan = planRepository.findById(subscription.getPlanId())
//                .orElseThrow(() -> new IllegalArgumentException("Plan with id not found"));

        PlanDto plan = planDto.getBody();
        if (plan.getName().equals("Lite") && command.boost()) {
            throw new IllegalArgumentException("El plan Free no permite Boost");
        }




        // Inicializar el inicio del mes para contar productos publicados
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date initMonth = calendar.getTime();

        // Contar los productos publicados por el usuario en el mes actual
        Long countProductsPublished = productRepository.countByUserIdAndCreatedAtAfter(user.getBody().getId(), initMonth);
        System.out.println("Productos publicados este mes: " + countProductsPublished);

        // Verificar el límite de productos según el plan
        switch (plan.getName()) {
            case "Lite":
                if (countProductsPublished >= 3) {
                    throw new IllegalArgumentException("Has alcanzado el límite de productos para el plan Free este mes.");
                }
                break;
            case "Plus":
                if (countProductsPublished >= 10) {
                    throw new IllegalArgumentException("Has alcanzado el límite de productos para el plan Plus este mes.");
                }
                break;
            case "Premium":
                // Premium no tiene límite de productos
                break;
            default:
                throw new IllegalArgumentException("Plan desconocido.");
        }

        if (command.boost().equals(true)) {
            // Establecer el período de Boost según el plan
            Date initPeriod;
            if ("Lite".equals(plan.getName())) {
                initPeriod = null; // El plan Free no permite Boost, así que no hay periodo de Boost.
            } else if ("Plus".equals(plan.getName())) {
                initPeriod = Date.from(LocalDateTime.now().minusDays(7).atZone(ZoneId.systemDefault()).toInstant());
            } else if ("Premium".equals(plan.getName())) {
                initPeriod = Date.from(LocalDateTime.now().minusDays(1).atZone(ZoneId.systemDefault()).toInstant());
            } else {
                throw new IllegalArgumentException("Plan desconocido.");
            }

            // Si el plan permite Boost, contar cuántos Boost se han usado en el período
            if (initPeriod != null) {
                Long countUsedBoosts = productRepository.countBoostsByUserIdAndCreatedAtAfter(user.getBody().getId(), initPeriod);
                if ((plan.getName().equals("Plus") && countUsedBoosts >= 3) ||
                        (plan.getName().equals("Premium") && countUsedBoosts >= 1)) {
                    throw new IllegalArgumentException("Has alcanzado el límite de Boost para tu plan.");
                }
            }
        }

        ProductCategory productCategory = productCategoryRepository.findById(command.productCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Product Category with id not found"));
        District district = districtRepository.findById(command.districtId())
                .orElseThrow(() -> new IllegalArgumentException("District with id not found"));

        try {
            var product = new Product(command, productCategory, user.getBody().getId(), district);
            productRepository.save(product);
            return Optional.of(product);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while creating product: " + e.getMessage());
        }
    }catch (FeignException.NotFound e){
        throw new IllegalArgumentException("User not found");
    }catch (FeignException e){
        throw new IllegalArgumentException("Error while creating product: " + e.getMessage());
    }

    }

    private void validatePlanRestrictions(PlanDto plan, CreateProductCommand command) {
        // Validar boost según el plan
        if ("Lite".equals(plan.getName()) && command.boost()) {
            throw new IllegalArgumentException("El plan Free no permite Boost");
        }

        // Validar límite de productos
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        long productsThisMonth = productRepository.countByUserIdAndCreatedAtAfter(
                command.userId(),
                calendar.getTime()
        );

        switch (plan.getName()) {
            case "Lite" -> {
                if (productsThisMonth >= 3) {
                    throw new IllegalArgumentException("Has alcanzado el límite de productos para el plan Free este mes");
                }
            }
            case "Plus" -> {
                if (productsThisMonth >= 10) {
                    throw new IllegalArgumentException("Has alcanzado el límite de productos para el plan Plus este mes");
                }
            }
            case "Premium" -> {
                // Sin límite
            }
            default -> throw new IllegalArgumentException("Plan desconocido");
        }
    }



@Override
    public Optional<Product>handle(UpdateProductCommand command){

        var result=productRepository.findById(command.id());

        if(result.isEmpty()){
            throw new IllegalArgumentException("Product with id not found");
        }

        var productToUpdate=result.get();

        try {
            ProductCategory productCategory = productCategoryRepository.findById(command.productCategoryId()).
                    orElseThrow(() -> new IllegalArgumentException("Product Category with id not found"));

            District district = districtRepository.findById(command.districtId()).
                    orElseThrow(() -> new IllegalArgumentException("District with id not found"));

            var updateProduct=productRepository.save(
                    productToUpdate.updateInformation(command.name(), command.description(),
                            command.desiredObject(), command.price(), command.image(), command.boost(),
                            command.available(), productCategory, command.userId(), district)
            );
            return Optional.of(updateProduct);
        }catch (Exception e){
            throw new IllegalArgumentException("Error while updating product: " + e.getMessage());
        }

    }





//    @Override
//    public boolean handleDeleteProduct(DeleteProductOfPendingExchangesCommand command) {
//        Optional<Product> product = productRepository.findById(command.id());
//        if (product.isEmpty()) {
//            throw new IllegalArgumentException("Product not found");
//        }
//        List<FavoriteProduct> favoriteProducts = favoriteProductRepository.findFavoriteProductsByProductId(product.get());
//
//        List<Exchange> exchanges = exchangeRepository.findAllExchangesByProductOwnIdOrProductChangeId(product.get());
//
//        exchangeRepository.deleteAll(exchanges);
//        favoriteProductRepository.deleteAll(favoriteProducts);
//        productRepository.delete(product.get());
//        return true;
//    }
}
