package com.cambiazo.product.interfaces.rest.transform;

import com.cambiazo.product.domain.model.commands.CreateDistrictCommand;
import com.cambiazo.product.interfaces.rest.resources.CreateDistrictResource;

public class CreateDistrictCommandFromResourceAssembler {
    public static CreateDistrictCommand toCommandFromResource(CreateDistrictResource districtResource) {
        return new CreateDistrictCommand(districtResource.name(), districtResource.departmentId());
    }
}
