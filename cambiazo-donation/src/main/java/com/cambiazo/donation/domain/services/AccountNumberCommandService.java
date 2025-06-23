package com.cambiazo.donation.domain.services;

import com.cambiazo.donation.domain.model.aggregates.AccountNumber;
import com.cambiazo.donation.domain.model.commands.CreateAccountNumberCommand;

import java.util.Optional;

public interface AccountNumberCommandService {

    Optional<AccountNumber>handle(CreateAccountNumberCommand command);
    boolean handleDeleteAccountNumber(Long id);
}
