package com.cambiazo.donation.infrastructure.persistence.jpa;

import com.cambiazo.donation.domain.model.aggregates.AccountNumber;
import com.cambiazo.donation.domain.model.aggregates.Ong;
import com.cambiazo.donation.domain.model.valueobjects.AccountNumberAccount;
import com.cambiazo.donation.domain.model.valueobjects.AccountNumberCci;
import com.cambiazo.donation.domain.model.valueobjects.AccountNumberName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountNumberRepository extends JpaRepository<AccountNumber, Long>{

    Optional<AccountNumber>findByNameAndCciAndAccount(AccountNumberName name, AccountNumberCci cci, AccountNumberAccount account);

    List<AccountNumber>findByOngId(Ong id);
}
