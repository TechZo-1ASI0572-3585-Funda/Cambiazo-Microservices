package com.cambiazo.donation.infrastructure.persistence.jpa;

import com.cambiazo.donation.domain.model.aggregates.Ong;
import com.cambiazo.donation.domain.model.aggregates.SocialNetwork;
import com.cambiazo.donation.domain.model.valueobjects.SocialNetworkName;
import com.cambiazo.donation.domain.model.valueobjects.SocialNetworkUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SocialNetworkRepository extends JpaRepository<SocialNetwork, Long>{

    Optional<SocialNetwork>findByNameAndUrl(SocialNetworkName name, SocialNetworkUrl url);

    List<SocialNetwork> findByOngId(Ong id);
}
