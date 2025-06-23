package com.cambiazo.donation.infrastructure.persistence.jpa;

import com.cambiazo.donation.domain.model.aggregates.Ong;
import com.cambiazo.donation.domain.model.aggregates.Project;
import com.cambiazo.donation.domain.model.valueobjects.ProjectName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository  extends JpaRepository<Project, Long> {

    Optional<Project>findByName(ProjectName name);

    List<Project>findByOngId(Ong id);
}
