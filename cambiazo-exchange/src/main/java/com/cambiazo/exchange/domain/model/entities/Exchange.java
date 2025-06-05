package com.cambiazo.exchange.domain.model.entities;

import com.cambiazo.exchange.domain.model.commands.CreateExchangeCommand;
import com.cambiazo.exchange.shared.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Entity
public class Exchange extends AuditableAbstractAggregateRoot<Exchange> {
    @Column(nullable = true)
    private LocalDate exchangeDate;

    @Column(name = "product_own_id", nullable = false)
    private Long productOwnId;

    @Column(name = "product_change_id", nullable = false)
    private Long productChangeId;

    @Column(nullable = false)
    @NotNull(message = "Status is mandatory")
    private String status;

    @Column(name="user_own_id", nullable = false)
    private Long userOwnId;

    @Column(name="user_change_id", nullable = false)
    private Long userChangeId;

    public Exchange() {
    }

    public Exchange(CreateExchangeCommand command, Long userOwnId, Long userChangeId) {
        this.exchangeDate=null;
        this.productOwnId = command.productOwnId();
        this.productChangeId = command.productChangeId();
        this.status = command.status();
        this.userOwnId = userOwnId;
        this.userChangeId = userChangeId;
    }

    public Exchange updateInformation(Long productOwnId, Long productChangeId, String status) {
        this.productOwnId = productOwnId;
        this.productChangeId = productChangeId;
        this.status = status;
        return this;
    }

}
