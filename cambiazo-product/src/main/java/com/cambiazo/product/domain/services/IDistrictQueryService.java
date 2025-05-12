package com.cambiazo.product.domain.services;

import com.cambiazo.product.domain.model.entities.District;
import com.cambiazo.product.domain.model.queries.GetAllDistrictsQuery;
import com.cambiazo.product.domain.model.queries.GetDistrictByIdQuery;

import java.util.List;
import java.util.Optional;

public interface IDistrictQueryService {

    Optional<District> handle(GetDistrictByIdQuery query);

    List<District>handle(GetAllDistrictsQuery query);
}
