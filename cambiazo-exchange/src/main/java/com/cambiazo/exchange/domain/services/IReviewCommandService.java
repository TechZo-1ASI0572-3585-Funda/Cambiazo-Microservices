package com.cambiazo.exchange.domain.services;


import com.cambiazo.exchange.domain.model.commands.CreateReviewCommand;
import com.cambiazo.exchange.domain.model.entities.Review;

import java.util.Optional;

public interface IReviewCommandService {
    Optional<Review>handle(CreateReviewCommand command);

    boolean handleDeleteReview(Long id);
}
