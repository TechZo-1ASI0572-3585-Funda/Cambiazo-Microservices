package com.cambiazo.donation.application.internal.queryservices;

import com.cambiazo.donation.domain.model.aggregates.Ong;
import com.cambiazo.donation.domain.model.aggregates.Project;
import com.cambiazo.donation.domain.model.queries.GetAllProjectsQuery;
import com.cambiazo.donation.domain.model.queries.GetProjectByIdQuery;
import com.cambiazo.donation.domain.model.queries.GetProjectsByOngIdQuery;
import com.cambiazo.donation.domain.services.ProjectQueryService;
import com.cambiazo.donation.infrastructure.persistence.jpa.OngRepository;
import com.cambiazo.donation.infrastructure.persistence.jpa.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ProjectQueryServiceImpl implements ProjectQueryService {

    private final ProjectRepository projectRepository;

    private final OngRepository ongRepository;

    public ProjectQueryServiceImpl(ProjectRepository projectRepository, OngRepository ongRepository) {
        this.projectRepository = projectRepository;
        this.ongRepository = ongRepository;
    }

    @Override
    public List<Project> handle(GetAllProjectsQuery query) {
        return projectRepository.findAll();
    }

    @Override
    public Optional<Project> handle(GetProjectByIdQuery query) {
        return projectRepository.findById(query.id());
    }

    @Override
    public List<Project>handle(GetProjectsByOngIdQuery query) {
        Ong ong = ongRepository.findById(query.ongId())
                .orElseThrow(() -> new IllegalArgumentException("Ong with id " + query.ongId() + " not found"));
        return projectRepository.findByOngId(ong);
    }

}

