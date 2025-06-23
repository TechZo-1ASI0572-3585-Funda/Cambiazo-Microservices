package com.cambiazo.donation.domain.services;

import com.cambiazo.donation.domain.model.aggregates.Ong;
import com.cambiazo.donation.domain.model.commands.CreateOngCommand;
import com.cambiazo.donation.domain.model.commands.UpdateOngCommand;

import java.util.Optional;

public interface OngCommandService {
    Optional<Ong>handle(CreateOngCommand command);
    Optional<Ong>handle(UpdateOngCommand command);
    boolean handleDeleteOng(Long id);
}
