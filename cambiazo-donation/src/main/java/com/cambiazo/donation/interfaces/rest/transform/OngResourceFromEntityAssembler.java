package com.cambiazo.donation.interfaces.rest.transform;

import com.cambiazo.donation.domain.model.aggregates.Ong;
import com.cambiazo.donation.interfaces.rest.resources.OngResource;

public class OngResourceFromEntityAssembler {
    public static OngResource toResourceFromEntity(Ong entity){
        return new OngResource(
                entity.getId(),
                entity.getName(),
                entity.getType(),
                entity.getAboutUs(),
                entity.getMissionAndVision(),
                entity.getSupportForm(),
                entity.getAddress(),
                entity.getEmail(),
                entity.getPhone(),
                entity.getLogo(),
                entity.getWebsite(),
                entity.getCategoryOngId().getCategoryId(),
                entity.getSchedule()
        );
    }
}
