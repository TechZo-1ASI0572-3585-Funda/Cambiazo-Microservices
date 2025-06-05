package com.cambiazo.exchange.domain.model.dtos;

import com.cambiazo.exchange.domain.model.entities.Exchange;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Data
@AllArgsConstructor
public class ModifiedExchange {

    private Long id;
    private ProductDto productOwn;
    private ProductDto productChange;
    private UserDto userOwn;
    private UserDto userChange;
    private String status;
    private LocalDate exchangeDate;
    private Date createdAt;
    private Date updatedAt;

    public ModifiedExchange(){}


    public ModifiedExchange(Exchange exchange, ProductDto productOwn, ProductDto productChange, UserDto userOwn, UserDto userChange) {
        this.id = exchange.getId();
        this.productOwn = productOwn;
        this.productChange = productChange;
        this.status = exchange.getStatus();
        this.exchangeDate = exchange.getExchangeDate();
        this.createdAt = exchange.getCreatedAt();
        this.updatedAt = exchange.getUpdatedAt();
        this.userOwn = userOwn;
        this.userChange = userChange;
    }
}
