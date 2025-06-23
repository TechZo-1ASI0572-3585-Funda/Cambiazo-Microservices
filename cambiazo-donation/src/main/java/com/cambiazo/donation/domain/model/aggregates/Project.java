package com.cambiazo.donation.domain.model.aggregates;

import com.cambiazo.donation.domain.model.commands.CreateProjectCommand;
import com.cambiazo.donation.domain.model.valueobjects.ProjectName;
import com.cambiazo.donation.shared.aggregates.AuditableAbstractAggregateRoot;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

/**
 * Represents a project entity.
 *
 * @author CambiaZo - TechZo
 * @version 1.0
 */
@Entity
public class Project extends AuditableAbstractAggregateRoot<Project> {

    @Embedded
    @Column(nullable = false)
    @NotNull(message = "Name is mandatory")
    private ProjectName name;

    @Column(nullable = false, columnDefinition = "TEXT")
    @NotNull(message = "Description is mandatory")
    @Getter
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ong_id")
    @NotNull(message = "Ong is mandatory")
    @JsonBackReference("ong-project")
    private Ong ongId;

    public Project() {}

    /**
     * Constructor to create a Project object from a command and an Ong entity.
     *
     * @param command The create project command.
     * @param ong     The Ong entity associated with the project.
     */
    public Project(CreateProjectCommand command, Ong ong) {
        this.name = new ProjectName(command.name());
        this.description = command.description();
        this.ongId = ong;
    }

    /**
     * Get the ID of the Ong associated with the project.
     *
     * @return The ID of the Ong.
     */
    public Long getOngId() {
        return ongId.getId();
    }

    /**
     * Get the name of the project.
     *
     * @return The name of the project.
     */
    public String getName() {
        return name.getProjectName();
    }

}
