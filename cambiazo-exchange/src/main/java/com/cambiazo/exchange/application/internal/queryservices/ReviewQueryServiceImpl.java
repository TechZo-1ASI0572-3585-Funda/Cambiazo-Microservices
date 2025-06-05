package com.cambiazo.exchange.application.internal.queryservices;

import com.cambiazo.exchange.client.UserClient;
import com.cambiazo.exchange.domain.model.dtos.AverageAndCountReviewsDto;
import com.cambiazo.exchange.domain.model.dtos.ExistReview;
import com.cambiazo.exchange.domain.model.dtos.ReviewDto;
import com.cambiazo.exchange.domain.model.dtos.UserDto;
import com.cambiazo.exchange.domain.model.entities.Exchange;
import com.cambiazo.exchange.domain.model.entities.Review;
import com.cambiazo.exchange.domain.model.queries.FindReviewByUserAuthorIdAndExchangeId;
import com.cambiazo.exchange.domain.model.queries.GetAllReviewsByUserReceptorIdQuery;
import com.cambiazo.exchange.domain.model.queries.GetAverageRatingAndCountReviewsUserQuery;
import com.cambiazo.exchange.domain.services.IReviewQueryService;
import com.cambiazo.exchange.infrastructure.persistence.jpa.IExchangeRepository;
import com.cambiazo.exchange.infrastructure.persistence.jpa.IReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewQueryServiceImpl implements IReviewQueryService {

    private final IReviewRepository reviewRepository;

    private final UserClient userRepository;

    private final IExchangeRepository exchangeRepository;

    public ReviewQueryServiceImpl(IReviewRepository reviewRepository, IExchangeRepository exchangeRepository, UserClient userRepository) {
        this.reviewRepository = reviewRepository;
        this.exchangeRepository = exchangeRepository;
        this.userRepository = userRepository;
    }


    @Override
    public List<ReviewDto> handle(GetAllReviewsByUserReceptorIdQuery query) {
        var userReceptor = this.userRepository.getUserById(query.userReceptorId());
        if(userReceptor.getBody()==null) throw new IllegalArgumentException("User not found");
        List<Review> reviews = this.reviewRepository.findReviewsByUserReceptorId(userReceptor.getBody().id());
        return reviews.stream().map(review -> {
            var userAuthor = this.userRepository.getUserById(review.getUserAuthorId());
            if(userAuthor.getBody()==null) throw new IllegalArgumentException("User not found");
            return new ReviewDto(
                    review.getId(),
                    review.getMessage(),
                    review.getRating(),
                    review.getState(),
                    review.getExchangeId().getId(),
                    userAuthor.getBody(),
                    userReceptor.getBody()
            );
        }).collect(Collectors.toList());
    }


    @Override
    public AverageAndCountReviewsDto getAverageRatingAndCountReviewsByUserReceptorId(GetAverageRatingAndCountReviewsUserQuery query) {
        var user = this.userRepository.getUserById(query.userId());
        if(user.getBody()==null) throw new IllegalArgumentException("User not found");
        List<Review> reviews = this.reviewRepository.findReviewsByUserReceptorId(user.getBody().id());


        if(reviews.isEmpty()){
            return new AverageAndCountReviewsDto(0.0,0L);
        }else {
            return new AverageAndCountReviewsDto(reviews.stream().mapToDouble(Review::getRating).average().getAsDouble(),(long)reviews.size());
        }
    }

    @Override
    public ExistReview existsByUserAuthorIdAndExchangeId(FindReviewByUserAuthorIdAndExchangeId query) {
        var userAuthor = this.userRepository.getUserById(query.userAuthorId());
        if(userAuthor.getBody()==null) throw new IllegalArgumentException("User not found");

        Exchange exchange = this.exchangeRepository.findById(query.exchangeId())
                .orElseThrow(()->new IllegalArgumentException("Exchange not found"));

        return new ExistReview(this.reviewRepository.findReviewByUserAuthorIdAndExchangeId(userAuthor.getBody().id(), exchange)!=null);
    }

}
