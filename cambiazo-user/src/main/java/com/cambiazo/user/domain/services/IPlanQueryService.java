package com.cambiazo.user.domain.services;

import com.cambiazo.user.domain.model.dto.PlanDto;
import com.cambiazo.user.domain.model.queries.GetAllPlansQuery;
import com.cambiazo.user.domain.model.queries.GetPlanByIdQuery;

import java.util.List;
import java.util.Optional;

public interface IPlanQueryService {

    Optional<PlanDto>handle(GetPlanByIdQuery query);

    List<PlanDto>handle(GetAllPlansQuery query);
}
