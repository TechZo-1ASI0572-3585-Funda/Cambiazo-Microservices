package com.cambiazo.donation.domain.model.dtos;


import com.cambiazo.donation.domain.model.aggregates.AccountNumber;
import com.cambiazo.donation.domain.model.aggregates.Project;
import com.cambiazo.donation.domain.model.aggregates.SocialNetwork;
import com.cambiazo.donation.domain.model.entities.CategoryOng;

import java.util.Set;

public record OngResponse(
        Long id,
        String name,
        String type,
        String aboutUs,
        String missionAndVision,
        String supportForm,
        String address,
        String email,
        String phone,
        String logo,
        String website,
        String schedule,
        CategoryOng categoryOngId,
        Set<Project> projects,
        Set<AccountNumber> accountNumbers,
        Set<SocialNetwork> socialNetworks
) {}


