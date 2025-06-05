package com.cambiazo.exchange.domain.services;

import com.cambiazo.exchange.domain.model.dtos.ModifiedExchange;
import com.cambiazo.exchange.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface IExchangeQueryService {

    Optional<ModifiedExchange>handle(GetExchangeByIdQuery query);

    List<ModifiedExchange>handle(GetAllExchangesQuery query);

    List<ModifiedExchange>handle(GetAllExchangesByUserOwnIdQuery query);

    List<ModifiedExchange>handle(GetAllExchangesByUserChangeIdQuery query);

    List<ModifiedExchange>handle(GetAllFinishedExchangesByUserIdQuery query);
}
