package com.cambiazo.donation.domain.services;

import com.cambiazo.donation.domain.model.aggregates.Project;
import com.cambiazo.donation.domain.model.queries.GetAllProjectsQuery;
import com.cambiazo.donation.domain.model.queries.GetProjectByIdQuery;
import com.cambiazo.donation.domain.model.queries.GetProjectsByOngIdQuery;

import java.util.List;
import java.util.Optional;

public interface ProjectQueryService {

    List<Project>handle(GetProjectsByOngIdQuery query);
    List<Project>handle(GetAllProjectsQuery query);
    Optional<Project>handle(GetProjectByIdQuery query);
}
