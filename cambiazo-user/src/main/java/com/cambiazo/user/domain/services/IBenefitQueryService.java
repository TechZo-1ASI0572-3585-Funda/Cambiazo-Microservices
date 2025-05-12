package com.cambiazo.user.domain.services;

import com.cambiazo.user.domain.model.entities.Benefit;
import com.cambiazo.user.domain.model.queries.GetAllBenefitsQuery;
import com.cambiazo.user.domain.model.queries.GetBenefitByIdQuery;

import java.util.List;
import java.util.Optional;

public interface IBenefitQueryService {

    Optional<Benefit>handle(GetBenefitByIdQuery query);

    List<Benefit>handle(GetAllBenefitsQuery query);
}
