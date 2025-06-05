package com.cambiazo.exchange.domain.services;


import com.cambiazo.exchange.domain.model.dtos.AverageAndCountReviewsDto;
import com.cambiazo.exchange.domain.model.dtos.ExistReview;
import com.cambiazo.exchange.domain.model.dtos.ReviewDto;
import com.cambiazo.exchange.domain.model.queries.FindReviewByUserAuthorIdAndExchangeId;
import com.cambiazo.exchange.domain.model.queries.GetAllReviewsByUserReceptorIdQuery;
import com.cambiazo.exchange.domain.model.queries.GetAverageRatingAndCountReviewsUserQuery;

import java.util.List;

public interface IReviewQueryService {

    List<ReviewDto>handle(GetAllReviewsByUserReceptorIdQuery query);
    ExistReview existsByUserAuthorIdAndExchangeId(FindReviewByUserAuthorIdAndExchangeId query);
    AverageAndCountReviewsDto getAverageRatingAndCountReviewsByUserReceptorId(GetAverageRatingAndCountReviewsUserQuery query);
}
