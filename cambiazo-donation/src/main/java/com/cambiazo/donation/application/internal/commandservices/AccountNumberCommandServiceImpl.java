package com.cambiazo.donation.application.internal.commandservices;

import com.cambiazo.donation.domain.exceptions.OngNotFoundException;
import com.cambiazo.donation.domain.model.aggregates.AccountNumber;
import com.cambiazo.donation.domain.model.aggregates.Ong;
import com.cambiazo.donation.domain.model.commands.CreateAccountNumberCommand;
import com.cambiazo.donation.domain.model.valueobjects.AccountNumberAccount;
import com.cambiazo.donation.domain.model.valueobjects.AccountNumberCci;
import com.cambiazo.donation.domain.model.valueobjects.AccountNumberName;
import com.cambiazo.donation.domain.services.AccountNumberCommandService;
import com.cambiazo.donation.infrastructure.persistence.jpa.AccountNumberRepository;
import com.cambiazo.donation.infrastructure.persistence.jpa.OngRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountNumberCommandServiceImpl implements AccountNumberCommandService {

    private final AccountNumberRepository accountNumberRepository;

    private final OngRepository ongRepository;

    public AccountNumberCommandServiceImpl(AccountNumberRepository accountNumberRepository, OngRepository ongRepository) {
        this.accountNumberRepository = accountNumberRepository;
        this.ongRepository = ongRepository;
    }

    @Override
    public Optional<AccountNumber>handle(CreateAccountNumberCommand command) {
        Ong ong = ongRepository.findById(command.ongId())
                .orElseThrow(() -> new OngNotFoundException(command.ongId()));

        var name = new AccountNumberName(command.name());
        var cci = new AccountNumberCci(command.cci());
        var account = new AccountNumberAccount(command.account());
        accountNumberRepository.findByNameAndCciAndAccount(name, cci, account).ifPresent( accountNumber ->{
            throw new IllegalArgumentException("Account Number with name, cci and account already exists");
        });
        var accountNumber = new AccountNumber(command, ong);
        accountNumberRepository.save(accountNumber);
        return Optional.of(accountNumber);
    }

    @Override
    public boolean handleDeleteAccountNumber(Long id) {
        Optional<AccountNumber> accountNumber = accountNumberRepository.findById(id);
        if (accountNumber.isPresent()) {
            accountNumberRepository.delete(accountNumber.get());
            return true;
        } else {
            return false;
        }
    }
}
