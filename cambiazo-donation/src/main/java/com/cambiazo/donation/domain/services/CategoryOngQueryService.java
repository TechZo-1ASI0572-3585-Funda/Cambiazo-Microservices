package com.cambiazo.donation.domain.services;

import com.cambiazo.donation.domain.model.entities.CategoryOng;
import com.cambiazo.donation.domain.model.queries.GetAllCategoryOngsQuery;
import com.cambiazo.donation.domain.model.queries.GetCategoryOngByIdQuery;

import java.util.List;
import java.util.Optional;

public interface CategoryOngQueryService {
    List<CategoryOng> handle(GetAllCategoryOngsQuery query);
    Optional<CategoryOng> handle(GetCategoryOngByIdQuery query);
}
