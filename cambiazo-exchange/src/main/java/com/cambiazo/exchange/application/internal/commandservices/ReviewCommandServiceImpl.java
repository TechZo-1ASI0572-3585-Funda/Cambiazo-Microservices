package com.cambiazo.exchange.application.internal.commandservices;

import com.cambiazo.exchange.client.UserClient;
import com.cambiazo.exchange.domain.model.commands.CreateReviewCommand;
import com.cambiazo.exchange.domain.model.dtos.UserDto;
import com.cambiazo.exchange.domain.model.entities.Exchange;
import com.cambiazo.exchange.domain.model.entities.Review;
import com.cambiazo.exchange.domain.services.IReviewCommandService;
import com.cambiazo.exchange.infrastructure.persistence.jpa.IExchangeRepository;
import com.cambiazo.exchange.infrastructure.persistence.jpa.IReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReviewCommandServiceImpl implements IReviewCommandService {

    private final IReviewRepository reviewRepository;

    @Autowired
    private UserClient userRepository;

    private final IExchangeRepository exchangeRepository;

    public ReviewCommandServiceImpl(IReviewRepository reviewRepository, IExchangeRepository exchangeRepository) {
        this.reviewRepository = reviewRepository;
        this.exchangeRepository = exchangeRepository;
    }

    @Override
    public Optional<Review>handle(CreateReviewCommand command){
        Exchange exchange = exchangeRepository.findById(command.exchangeId()).orElseThrow(() -> new IllegalArgumentException("Exchange not found"));
        var userAuthor = userRepository.getUserById(command.userAuthorId());
        if(userAuthor.getBody()==null) throw new IllegalArgumentException("User not found");
        var userReceptor = userRepository.getUserById(command.userReceptorId());
        if(userReceptor.getBody()==null) throw new IllegalArgumentException("User not found");
        Review existsReview = reviewRepository.findReviewByUserAuthorIdAndExchangeId(userAuthor.getBody().id(), exchange);
        if(existsReview != null){
            throw new IllegalArgumentException("Review already exists");
        }
        var review = new Review(command, exchange, userAuthor.getBody().id(), userReceptor.getBody().id());
        var createdReview = reviewRepository.save(review);
        return Optional.of(createdReview);
    }

    @Override
    public boolean handleDeleteReview(Long id) {
        Optional<Review>review= reviewRepository.findById(id);
        if(review.isPresent()){
            reviewRepository.delete(review.get());
            return true;
        }else {
            throw new IllegalArgumentException("Review not found");
        }
    }
}
