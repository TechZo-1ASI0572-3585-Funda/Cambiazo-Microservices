package com.cambiazo.user.domain.services;

import com.cambiazo.user.domain.model.dto.SubscriptionDto;
import com.cambiazo.user.domain.model.queries.GetActiveSubscriptionByUserIdQuery;
import com.cambiazo.user.domain.model.queries.GetAllSubscriptionsQuery;
import com.cambiazo.user.domain.model.queries.GetSubscriptionByIdQuery;

import java.util.List;
import java.util.Optional;

public interface ISubscriptionQueryService {

    Optional<SubscriptionDto>handle(GetSubscriptionByIdQuery query);

    Optional<SubscriptionDto>handle(GetActiveSubscriptionByUserIdQuery query);
    List<SubscriptionDto>handle(GetAllSubscriptionsQuery query);
}
