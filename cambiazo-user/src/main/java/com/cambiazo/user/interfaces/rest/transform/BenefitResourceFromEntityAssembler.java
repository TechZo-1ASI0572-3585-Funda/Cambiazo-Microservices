package com.cambiazo.user.interfaces.rest.transform;


import com.cambiazo.user.domain.model.entities.Benefit;
import com.cambiazo.user.interfaces.rest.resources.BenefitResource;

public class BenefitResourceFromEntityAssembler {

    public static BenefitResource toResourceFromEntity(Benefit entity) {
        return new BenefitResource(entity.getId(), entity.getDescription(), entity.getPlanId());
    }
}
