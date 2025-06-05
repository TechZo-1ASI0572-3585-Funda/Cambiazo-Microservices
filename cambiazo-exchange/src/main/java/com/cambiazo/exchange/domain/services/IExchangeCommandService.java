package com.cambiazo.exchange.domain.services;


import com.cambiazo.exchange.domain.model.commands.CreateExchangeCommand;
import com.cambiazo.exchange.domain.model.commands.UpdateExchangeStatusCommand;
import com.cambiazo.exchange.domain.model.entities.Exchange;

import java.util.Optional;

public interface IExchangeCommandService {
    Optional<Exchange>handle(CreateExchangeCommand command);

    Optional<Exchange>handle(UpdateExchangeStatusCommand command);
    boolean handleDeleteExchange(Long id);
}
