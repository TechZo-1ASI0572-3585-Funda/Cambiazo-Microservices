package com.cambiazo.donation.application.internal.queryservices;


import com.cambiazo.donation.domain.model.aggregates.AccountNumber;
import com.cambiazo.donation.domain.model.aggregates.Ong;
import com.cambiazo.donation.domain.model.queries.GetAccountNumberByIdQuery;
import com.cambiazo.donation.domain.model.queries.GetAllAccountNumberByOngIdQuery;
import com.cambiazo.donation.domain.services.AccountNumberQueryService;
import com.cambiazo.donation.infrastructure.persistence.jpa.AccountNumberRepository;
import com.cambiazo.donation.infrastructure.persistence.jpa.OngRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountNumberQueryServiceImpl implements AccountNumberQueryService {

    private final AccountNumberRepository accountNumberRepository;

    private final OngRepository ongRepository;

    public AccountNumberQueryServiceImpl(AccountNumberRepository accountNumberRepository, OngRepository ongRepository) {
        this.accountNumberRepository = accountNumberRepository;
        this.ongRepository = ongRepository;
    }

    @Override
    public Optional<AccountNumber> handle(GetAccountNumberByIdQuery query) {
        return accountNumberRepository.findById(query.id());
    }

    @Override
    public List<AccountNumber> handle(GetAllAccountNumberByOngIdQuery query) {
        Ong ong = ongRepository.findById(query.ongId())
                .orElseThrow(() -> new IllegalArgumentException("Ong with id " + query.ongId() + " not found"));
        return accountNumberRepository.findByOngId(ong);
    }
}
