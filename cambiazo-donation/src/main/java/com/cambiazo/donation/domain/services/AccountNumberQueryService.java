package com.cambiazo.donation.domain.services;

import com.cambiazo.donation.domain.model.aggregates.AccountNumber;
import com.cambiazo.donation.domain.model.queries.GetAccountNumberByIdQuery;
import com.cambiazo.donation.domain.model.queries.GetAllAccountNumberByOngIdQuery;

import java.util.List;
import java.util.Optional;

public interface AccountNumberQueryService {
    Optional<AccountNumber>handle(GetAccountNumberByIdQuery query);
    List<AccountNumber>handle(GetAllAccountNumberByOngIdQuery query);
}
