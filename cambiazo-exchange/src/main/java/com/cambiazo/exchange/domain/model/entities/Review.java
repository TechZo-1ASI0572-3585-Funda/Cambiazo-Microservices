package com.cambiazo.exchange.domain.model.entities;

import com.cambiazo.exchange.domain.model.commands.CreateReviewCommand;
import com.cambiazo.exchange.shared.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Review extends AuditableAbstractAggregateRoot<Review> {

    @Column(name = "message", columnDefinition = "TEXT")
    @NotNull(message = "Message is mandatory")
    private String message;

    @Column(name = "rating", nullable = false)
    @NotNull(message = "Rating is mandatory")
    private Integer rating;

    @Column(name = "state", nullable = false)
    @NotNull(message = "State is mandatory")
    private String state;

    @ManyToOne
    @JoinColumn(name = "exchange_id", nullable = false)
    private Exchange exchangeId;

    @Column(name = "user_author_id", nullable = false)
    private Long userAuthorId;

    @Column(name = "user_receptor_id", nullable = false)
    private Long userReceptorId;

    public Review() {
    }

    public Review(CreateReviewCommand command, Exchange exchangeId, Long userAuthorId, Long userReceptorId) {
        this.message = command.message();
        this.rating = command.rating();
        this.state = command.state();
        this.exchangeId = exchangeId;
        this.userAuthorId = userAuthorId;
        this.userReceptorId = userReceptorId;
    }

}
