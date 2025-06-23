package com.cambiazo.donation.domain.services;


import com.cambiazo.donation.domain.model.aggregates.Project;
import com.cambiazo.donation.domain.model.commands.CreateProjectCommand;

import java.util.Optional;

public interface ProjectCommandService {
    Optional<Project> handle(CreateProjectCommand command);
    boolean handleDeleteProject(Long id);
}
