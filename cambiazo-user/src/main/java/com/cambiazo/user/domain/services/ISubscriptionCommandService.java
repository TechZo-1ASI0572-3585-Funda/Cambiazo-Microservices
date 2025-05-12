package com.cambiazo.user.domain.services;

import com.cambiazo.user.domain.model.commands.CreateSubscriptionCommand;
import com.cambiazo.user.domain.model.commands.UpdateSubscriptionCommand;
import com.cambiazo.user.domain.model.entities.Subscription;

import java.util.Optional;

public interface ISubscriptionCommandService {
    Optional<Subscription>handle(CreateSubscriptionCommand command);

    Optional<Subscription>handle(UpdateSubscriptionCommand command);
}
