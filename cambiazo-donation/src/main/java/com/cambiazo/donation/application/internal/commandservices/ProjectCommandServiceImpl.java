package com.cambiazo.donation.application.internal.commandservices;


import com.cambiazo.donation.domain.exceptions.OngNotFoundException;
import com.cambiazo.donation.domain.model.aggregates.Ong;
import com.cambiazo.donation.domain.model.aggregates.Project;
import com.cambiazo.donation.domain.model.commands.CreateProjectCommand;
import com.cambiazo.donation.domain.services.ProjectCommandService;
import com.cambiazo.donation.infrastructure.persistence.jpa.OngRepository;
import com.cambiazo.donation.infrastructure.persistence.jpa.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjectCommandServiceImpl implements ProjectCommandService{
    private final ProjectRepository projectRepository;

    private final OngRepository ongRepository;

    public ProjectCommandServiceImpl(ProjectRepository projectRepository, OngRepository ongRepository) {
        this.projectRepository = projectRepository;
        this.ongRepository = ongRepository;
    }

    @Override
    public Optional<Project> handle(CreateProjectCommand command) {
        Ong ong = ongRepository.findById(command.ongId())
                .orElseThrow(() -> new OngNotFoundException(command.ongId()));

        var project = new Project(command, ong);
        projectRepository.save(project);
        return Optional.of(project);
    }

    @Override
    public boolean handleDeleteProject(Long id) {
        Optional<Project> project = projectRepository.findById(id);
        if (project.isPresent()) {
            projectRepository.delete(project.get());
            return true;
        } else {
            return false;
        }
    }
}
