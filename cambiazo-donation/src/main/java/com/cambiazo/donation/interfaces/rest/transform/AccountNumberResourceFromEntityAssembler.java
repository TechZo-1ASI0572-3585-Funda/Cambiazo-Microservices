package com.cambiazo.donation.interfaces.rest.transform;

import com.cambiazo.donation.domain.model.aggregates.AccountNumber;
import com.cambiazo.donation.interfaces.rest.resources.AccountNumberResource;

public class AccountNumberResourceFromEntityAssembler {

    public static AccountNumberResource toResourceFromEntity(AccountNumber entity) {
        return new AccountNumberResource(entity.getId(), entity.getName(), entity.getCci(), entity.getAccount(), entity.getOngId());
    }
}
