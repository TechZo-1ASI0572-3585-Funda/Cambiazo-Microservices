package com.cambiazo.user.interfaces.rest.transform;

import com.cambiazo.user.domain.model.entities.Plan;
import com.cambiazo.user.interfaces.rest.resources.PlanResource;

public class PlanResourceFromEntityAssembler {

    public static PlanResource toResourceFromEntity(Plan entity) {
        return new PlanResource(entity.getId(), entity.getName(), entity.getDescription(), entity.getPrice());
    }
}
