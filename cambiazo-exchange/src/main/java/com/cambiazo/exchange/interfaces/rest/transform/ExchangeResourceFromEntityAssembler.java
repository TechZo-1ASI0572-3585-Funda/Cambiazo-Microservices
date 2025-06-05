package com.cambiazo.exchange.interfaces.rest.transform;


import com.cambiazo.exchange.domain.model.entities.Exchange;
import com.cambiazo.exchange.interfaces.rest.resources.ExchangeResource;

public class ExchangeResourceFromEntityAssembler {
        public static ExchangeResource toResourceFromEntity(Exchange entity) {
            return new ExchangeResource(entity.getId(), entity.getProductOwnId(), entity.getProductChangeId(), entity.getStatus(), entity.getExchangeDate(), entity.getCreatedAt(), entity.getUpdatedAt());
        }
}
