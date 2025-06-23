package com.cambiazo.donation.domain.services;

import com.cambiazo.donation.domain.model.aggregates.SocialNetwork;
import com.cambiazo.donation.domain.model.commands.CreateSocialNetworkCommand;

import java.util.Optional;

public interface SocialNetworkCommandService {

    Optional<SocialNetwork> handle(CreateSocialNetworkCommand command);
    boolean handleDeleteSocialNetwork(Long id);
}
