package com.cambiazo.donation.application.internal.queryservices;

import com.cambiazo.donation.domain.model.aggregates.Ong;
import com.cambiazo.donation.domain.model.aggregates.SocialNetwork;
import com.cambiazo.donation.domain.model.queries.GetAllSocialNetworkByOngIdQuery;
import com.cambiazo.donation.domain.model.queries.GetAllSocialNetworksQuery;
import com.cambiazo.donation.domain.model.queries.GetSocialNetworkByIdQuery;
import com.cambiazo.donation.domain.services.SocialNetworkQueryService;
import com.cambiazo.donation.infrastructure.persistence.jpa.OngRepository;
import com.cambiazo.donation.infrastructure.persistence.jpa.SocialNetworkRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SocialNetworkQueryServiceImpl implements SocialNetworkQueryService{

    private final SocialNetworkRepository socialNetworkRepository;

    private final OngRepository ongRepository;

    public SocialNetworkQueryServiceImpl(SocialNetworkRepository socialNetworkRepository, OngRepository ongRepository) {
        this.socialNetworkRepository = socialNetworkRepository;
        this.ongRepository = ongRepository;
    }

    @Override
    public List<SocialNetwork> handle(GetAllSocialNetworksQuery query) {
        return socialNetworkRepository.findAll();
    }

    @Override
    public Optional<SocialNetwork> handle(GetSocialNetworkByIdQuery query) {
        return socialNetworkRepository.findById(query.id());
    }

    @Override
    public List<SocialNetwork> handle(GetAllSocialNetworkByOngIdQuery query) {
        Ong ong = ongRepository.findById(query.ongId())
                .orElseThrow(() -> new IllegalArgumentException("Ong with id " + query.ongId() + " not found"));
        return socialNetworkRepository.findByOngId(ong);
    }
}
