package com.cambiazo.donation.interfaces.rest.transform;


import com.cambiazo.donation.domain.model.aggregates.Project;
import com.cambiazo.donation.interfaces.rest.resources.ProjectResource;

public class ProjectResourceFromEntityAssembler {
    public static ProjectResource toResourceFromEntity(Project entity) {
        return new ProjectResource(entity.getId(), entity.getName(), entity.getDescription(), entity.getOngId());
    }
}
