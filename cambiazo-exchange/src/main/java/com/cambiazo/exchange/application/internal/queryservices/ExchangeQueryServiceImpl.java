package com.cambiazo.exchange.application.internal.queryservices;

import com.cambiazo.exchange.client.ProductClient;
import com.cambiazo.exchange.client.UserClient;
import com.cambiazo.exchange.domain.model.dtos.ModifiedExchange;
import com.cambiazo.exchange.domain.model.dtos.ProductDto;
import com.cambiazo.exchange.domain.model.dtos.UserDto;
import com.cambiazo.exchange.domain.model.entities.Exchange;
import com.cambiazo.exchange.domain.model.queries.*;
import com.cambiazo.exchange.domain.services.IExchangeQueryService;
import com.cambiazo.exchange.infrastructure.persistence.jpa.IExchangeRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExchangeQueryServiceImpl implements IExchangeQueryService {

    private final IExchangeRepository exchangeRepository;

    private final ProductClient productRepository;
    private final UserClient userRepository;


    public ExchangeQueryServiceImpl(IExchangeRepository exchangeRepository, ProductClient productRepository, UserClient userRepository) {
        this.exchangeRepository = exchangeRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }


    @Override
    public Optional<ModifiedExchange> handle(GetExchangeByIdQuery query) {
        Exchange exchange= this.exchangeRepository.findById(query.id())
                .orElseThrow(() -> new IllegalArgumentException("Exchange not found"));
        ProductDto productOwn = this.productRepository.getProductById(exchange.getProductOwnId()).getBody();
        if(productOwn==null) throw new IllegalArgumentException("Product not found");
        ProductDto productChange = this.productRepository.getProductById(exchange.getProductChangeId()).getBody();
        if(productChange==null) throw new IllegalArgumentException("Product not found");
        UserDto userOwn = this.userRepository.getUserById(productOwn.getUser().id()).getBody();
        UserDto userChange = this.userRepository.getUserById(productChange.getUser().id()).getBody();


        return Optional.of(
                new ModifiedExchange(
                        exchange,
                        productOwn,
                        productChange,
                        userOwn,
                        userChange
                )
        );
    }

    @Override
    public List<ModifiedExchange> handle(GetAllExchangesQuery query) {
        List<UserDto> users = this.userRepository.getAllUsers().getBody();
        System.out.println("USERS: "+ users);
        if(users==null) throw new IllegalArgumentException("Users not found");
        List<ProductDto> products = this.productRepository.getAllProducts().getBody();
        System.out.println("PRODUCTS: "+ products);
        if(products==null) throw new IllegalArgumentException("Products not found");
        List<Exchange> exchanges = this.exchangeRepository.findAll();


        return exchanges.stream().map(exchange -> {
            ProductDto productOwn = products.stream()
                    .filter(product -> product.getId().equals(exchange.getProductOwnId()))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Product not found"));
            ProductDto productChange = products.stream()
                    .filter(product -> product.getId().equals(exchange.getProductChangeId()))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Product not found"));
            UserDto userOwn = users.stream()
                    .filter(user -> user.id().equals(productOwn.getUser().id()))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));
            UserDto userChange = users.stream()
                    .filter(user -> user.id().equals(productChange.getUser().id()))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));


            return new ModifiedExchange(
                    exchange,
                    productOwn,
                    productChange,
                    userOwn,
                    userChange
            );
        }).collect(Collectors.toList());
    }

    @Override
    public List<ModifiedExchange> handle(GetAllExchangesByUserOwnIdQuery query) {
        UserDto userOwn = this.userRepository.getUserById(query.id()).getBody();
        if(userOwn==null) throw new IllegalArgumentException("User not found");

        
        List<ProductDto>products = this.productRepository.getAllProducts().getBody();
        if(products==null) throw new IllegalArgumentException("Products not found");
        List<Exchange>exchanges = this.exchangeRepository.findAllExchangesByUserOwnId(userOwn.id());

        return exchanges.stream().map(exchange -> {
            ProductDto productOwn = products.stream()
                    .filter(product -> product.getId().equals(exchange.getProductOwnId()))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Product not found"));
            ProductDto productChange = products.stream()
                    .filter(product -> product.getId().equals(exchange.getProductChangeId()))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Product not found"));
            UserDto userChange = this.userRepository.getUserById(productChange.getUser().id()).getBody();
            if(userChange==null) throw new IllegalArgumentException("User not found");

            return new ModifiedExchange(
                    exchange,
                    productOwn,
                    productChange,
                    userOwn,
                    userChange
            );
        }).collect(Collectors.toList());
    }

    @Override
    public List<ModifiedExchange> handle(GetAllExchangesByUserChangeIdQuery query) {
        UserDto user = this.userRepository.getUserById(query.id()).getBody();
        if(user==null) throw new IllegalArgumentException("User not found");

        List<ProductDto>products = this.productRepository.getAllProducts().getBody();
        if(products==null) throw new IllegalArgumentException("Products not found");
        List<Exchange>exchanges = this.exchangeRepository.findAllExchangesByUserChangeId(user.id());

        return exchanges.stream().map(exchange -> {
            ProductDto productOwn = products.stream()
                    .filter(product -> product.getId().equals(exchange.getProductOwnId()))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Product not found"));
            ProductDto productChange = products.stream()
                    .filter(product -> product.getId().equals(exchange.getProductChangeId()))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Product not found"));
            UserDto userOwn = this.userRepository.getUserById(productOwn.getUser().id()).getBody();
            if(userOwn==null) throw new IllegalArgumentException("User not found");


            return new ModifiedExchange(
                    exchange,
                    productOwn,
                    productChange,
                    userOwn,
                    user
            );
        }).collect(Collectors.toList());
    }

    @Override
    public List<ModifiedExchange> handle(GetAllFinishedExchangesByUserIdQuery query) {
        UserDto user = this.userRepository.getUserById(query.userId()).getBody();
        if(user==null) throw new IllegalArgumentException("User not found");


        List<ProductDto>products=this.productRepository.getAllProducts().getBody();
        if(products==null) throw new IllegalArgumentException("Products not found");

        List<Exchange> exchangesOwn = this.exchangeRepository.findAllExchangesByUserOwnId(user.id())
                .stream()
                .filter(exchange -> "Aceptado".equals(exchange.getStatus()))
                .toList();

        List<Exchange> exchangesChange = this.exchangeRepository.findAllExchangesByUserChangeId(user.id())
                .stream()
                .filter(exchange -> "Aceptado".equals(exchange.getStatus()))
                .toList();


        List<ModifiedExchange> modifiedExchangesOwn = exchangesOwn.stream().map(exchange -> {
            ProductDto productOwn = products.stream()
                    .filter(product -> product.getId().equals(exchange.getProductOwnId()))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Product not found"));
            ProductDto productChange = products.stream()
                    .filter(product -> product.getId().equals(exchange.getProductChangeId()))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Product not found"));
            UserDto userChange = this.userRepository.getUserById(productChange.getUser().id()).getBody();
            if(userChange==null) throw new IllegalArgumentException("User not found");


            return new ModifiedExchange(
                    exchange,
                    productOwn,
                    productChange,
                    user,
                    userChange
            );
        }).collect(Collectors.toList());


        List<ModifiedExchange>modifiedExchangesChange=exchangesChange.stream().map(exchange -> {
            ProductDto productOwn = products.stream()
                    .filter(product -> product.getId().equals(exchange.getProductOwnId()))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Product not found"));
            ProductDto productChange = products.stream()
                    .filter(product -> product.getId().equals(exchange.getProductChangeId()))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Product not found"));
            UserDto userChange = this.userRepository.getUserById(productOwn.getUser().id()).getBody();
            if(userChange==null) throw new IllegalArgumentException("User not found");


            return new ModifiedExchange(
                    exchange,
                    productOwn,
                    productChange,
                    userChange,
                    user
            );
        }).toList();


        // Combine both lists
        modifiedExchangesOwn.addAll(modifiedExchangesChange);

        return modifiedExchangesOwn.stream()
                .sorted(Comparator.comparing(ModifiedExchange::getExchangeDate).reversed())
                .collect(Collectors.toList());
    }

}
