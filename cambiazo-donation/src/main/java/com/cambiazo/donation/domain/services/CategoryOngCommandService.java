package com.cambiazo.donation.domain.services;

import com.cambiazo.donation.domain.model.commands.CreateCategoryOngCommand;
import com.cambiazo.donation.domain.model.commands.UpdateCategoryOngCommand;
import com.cambiazo.donation.domain.model.entities.CategoryOng;

import java.util.Optional;

public interface CategoryOngCommandService {
    Optional<CategoryOng> handle(CreateCategoryOngCommand command);
    Optional<CategoryOng>handle(UpdateCategoryOngCommand command);
    boolean handleDeleteCategoryOng(Long id);
}
