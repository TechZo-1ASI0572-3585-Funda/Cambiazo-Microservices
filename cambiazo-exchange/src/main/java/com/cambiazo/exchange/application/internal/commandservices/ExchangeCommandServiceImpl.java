package com.cambiazo.exchange.application.internal.commandservices;


import com.cambiazo.exchange.client.ProductClient;
import com.cambiazo.exchange.domain.model.commands.CreateExchangeCommand;
import com.cambiazo.exchange.domain.model.commands.UpdateExchangeStatusCommand;
import com.cambiazo.exchange.domain.model.dtos.ProductDto;
import com.cambiazo.exchange.domain.model.dtos.UpdateProductAvailabilityResource;
import com.cambiazo.exchange.domain.model.entities.Exchange;
import com.cambiazo.exchange.domain.services.IExchangeCommandService;
import com.cambiazo.exchange.infrastructure.persistence.jpa.IExchangeRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ExchangeCommandServiceImpl implements IExchangeCommandService {

    private final IExchangeRepository exchangeRepository;

    @Autowired
    private ProductClient productRepository;


    public ExchangeCommandServiceImpl(IExchangeRepository exchangeRepository) {
        this.exchangeRepository = exchangeRepository;

    }

    @Override
    public Optional<Exchange>handle(CreateExchangeCommand command) {
        try{
            ResponseEntity<ProductDto> productOwn= productRepository.getProductById(command.productOwnId());
            if(productOwn.getBody()==null) throw new IllegalArgumentException("Product not found");
            System.out.println("productOwn: " + productOwn.getBody());
            ResponseEntity<ProductDto>  productChange = productRepository.getProductById(command.productChangeId());
            if(productChange.getBody()==null) throw new IllegalArgumentException("Product not found");
            System.out.println("productChange: " + productChange.getBody());

            var result = exchangeRepository.findExchangeByProductOwnIdAndProductChangeId(productOwn.getBody().getId(), productChange.getBody().getId());
            if(result!=null) throw new IllegalArgumentException("Exchange already exists");

            try{
                System.out.println("product user OWN ID: " + productOwn.getBody().getUser().id());
                System.out.println("product user CHANGE ID: " + productChange.getBody().getUser().id());
                var exchange = new Exchange(command, productOwn.getBody().getUser().id(), productChange.getBody().getUser().id());
                var createdExchange = exchangeRepository.save(exchange);
                return Optional.of(createdExchange);
            }catch (Exception e){
                throw new IllegalArgumentException("Error while creating exchange: "+ e.getMessage());
            }
        }catch (FeignException e){
            throw new IllegalArgumentException("Product not found");
        }

    }

    @Override
    public Optional<Exchange>handle(UpdateExchangeStatusCommand command){
        var result = exchangeRepository.findById(command.id());
        if (result.isEmpty()) {
            throw new IllegalArgumentException("Exchange does not exist");
        }
        var exchangeToUpdate = result.get();
        try {

            ResponseEntity<ProductDto> productOwn = productRepository.getProductById(exchangeToUpdate.getProductOwnId());
            if(productOwn.getBody()==null) throw new IllegalArgumentException("Product not found");
            ResponseEntity<ProductDto> productChange = productRepository.getProductById(exchangeToUpdate.getProductChangeId());
            if(productChange.getBody()==null) throw new IllegalArgumentException("Product not found");


            System.out.println("Product own: " + productOwn.getBody());
            System.out.println("Product change: " + productChange.getBody());

            // Update exchange information
            exchangeToUpdate.updateInformation(productOwn.getBody().getId(), productChange.getBody().getId(), command.status());

            // Set exchange date if status is "Aceptado"
            if("Aceptado".equals(command.status())){
                exchangeToUpdate.setExchangeDate(java.time.LocalDate.now());
            }

            // Save the exchange with all updates
            var updatedExchange = exchangeRepository.save(exchangeToUpdate);
            System.out.println("=== LLEGO HASTA AQUI DESPUES DEL SAVE =====");

            if("Aceptado".equals(command.status())){
                System.out.println("=== LLEGO HASTA AQUI DESPUES DEL SET DATE =====");
                UpdateProductAvailabilityResource updateProductAvailabilityResource1 = new UpdateProductAvailabilityResource(productOwn.getBody().getId(),false);
                try {
                    System.out.println("=== INTENTANDO ACTUALIZAR PRODUCTO 1 CON: " + updateProductAvailabilityResource1);
                    ResponseEntity<Boolean> result1 = productRepository.updateProductAvailability(updateProductAvailabilityResource1);
                    if(result1.getStatusCode().isError()) throw new IllegalArgumentException("Error updating product availability");
                    System.out.println("=== LLEGO HASTA AQUI DESPUES DE ACTUALIZAR EL PRODUCTO 1 =====");

                    UpdateProductAvailabilityResource updateProductAvailabilityResource2 = new UpdateProductAvailabilityResource(productChange.getBody().getId(),false);
                    System.out.println("=== INTENTANDO ACTUALIZAR PRODUCTO 2 CON: " + updateProductAvailabilityResource2);
                    ResponseEntity<Boolean> result2 = productRepository.updateProductAvailability(updateProductAvailabilityResource2);
                    if(result2.getStatusCode().isError()) throw new IllegalArgumentException("Error updating product availability");
                    System.out.println("=== LLEGO HASTA AQUI DESPUES DE ACTUALIZAR EL PRODUCTO 2 =====");
                } catch (FeignException e) {
                    System.out.println("=== ERROR DE FEIGN: " + e.getMessage());
                    throw new IllegalArgumentException("Error updating product availability: " + e.getMessage());
                } catch (Exception e) {
                    System.out.println("=== ERROR GENERAL: " + e.getMessage());
                    throw new IllegalArgumentException("Error updating product availability: " + e.getMessage());
                }

            }
            //
            System.out.println("=== LLEGO HASTA AQUI DESPUES DE ACTUALIZAR LOS PRODUCTOS =====");
            exchangeRepository.updateExchangeStatusToRejectedByProductOwnExcept(
                    productOwn.getBody().getId(), exchangeToUpdate.getId());

            exchangeRepository.updateExchangeStatusToRejectedByProductChangeExcept(
                    productChange.getBody().getId(), exchangeToUpdate.getId());

            System.out.println("=== LLEGO HASTA AQUI DESPUES DE ACTUALIZAR EL ESTADO DE LOS EXCHANGES =====");
            return Optional.of(updatedExchange);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error updating exchange:" + e.getMessage());
        }
    }

    @Override
    public boolean handleDeleteExchange(Long id){
        Optional<Exchange>exchange = exchangeRepository.findById(id);
        if(exchange.isPresent() && exchange.get().getStatus().equals("Pendiente")){
            exchangeRepository.delete(exchange.get());
            return true;
        }else {
            throw new IllegalArgumentException("Exchange not found");
        }
    }
}
