package com.cambiazo.product.interfaces.rest.transform;

import com.cambiazo.product.domain.model.entities.District;
import com.cambiazo.product.interfaces.rest.resources.DistrictResource;

public class DistrictResourceFromEntityAssembler {
    public static DistrictResource toResourceFromEntity(District district) {
        return new DistrictResource(district.getId(), district.getName(), district.getDepartmentId());
    }
}
