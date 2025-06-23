package com.cambiazo.donation.domain.services;

import com.cambiazo.donation.domain.model.aggregates.SocialNetwork;
import com.cambiazo.donation.domain.model.queries.GetAllSocialNetworkByOngIdQuery;
import com.cambiazo.donation.domain.model.queries.GetAllSocialNetworksQuery;
import com.cambiazo.donation.domain.model.queries.GetSocialNetworkByIdQuery;

import java.util.List;
import java.util.Optional;

public interface SocialNetworkQueryService {
    Optional<SocialNetwork> handle(GetSocialNetworkByIdQuery query);
    List<SocialNetwork> handle(GetAllSocialNetworksQuery query);
    List<SocialNetwork> handle(GetAllSocialNetworkByOngIdQuery query);
}
