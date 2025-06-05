package com.cambiazo.exchange.infrastructure.persistence.jpa;

import com.cambiazo.exchange.domain.model.dtos.UserDto;
import com.cambiazo.exchange.domain.model.entities.Exchange;
import com.cambiazo.exchange.domain.model.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IReviewRepository extends JpaRepository<Review, Long> {

    List<Review>findReviewsByUserReceptorId(Long userReceptorId);

    Review findReviewByUserAuthorIdAndExchangeId(Long userAuthorId, Exchange exchangeId);
}
