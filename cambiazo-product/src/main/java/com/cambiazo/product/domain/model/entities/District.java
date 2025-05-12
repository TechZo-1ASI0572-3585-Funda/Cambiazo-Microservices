package com.cambiazo.product.domain.model.entities;

import com.cambiazo.product.domain.model.commands.CreateDistrictCommand;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "districts")
public class District {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull(message = "Name is required")
    private String name;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    @NotNull(message = "Department is required")
    private Department departmentId;

    public District() {
    }

    public District(CreateDistrictCommand command, Department department) {
        this.name = command.name();
        this.departmentId = department;
    }

    public Long getDepartmentId() {
        return departmentId.getId();
    }
}
