package com.cambiazo.user.application.internal.queryservices;

import com.cambiazo.user.domain.model.entities.Benefit;
import com.cambiazo.user.domain.model.queries.GetAllBenefitsQuery;
import com.cambiazo.user.domain.model.queries.GetBenefitByIdQuery;
import com.cambiazo.user.domain.services.IBenefitQueryService;
import com.cambiazo.user.infrastructure.persistence.jpa.repositories.IBenefitRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BenefitQueryServiceImpl implements IBenefitQueryService {

    private final IBenefitRepository benefitRepository;

    public BenefitQueryServiceImpl(IBenefitRepository benefitRepository){
        this.benefitRepository=benefitRepository;
    }


    @Override
    public Optional<Benefit> handle(GetBenefitByIdQuery query) {
        return benefitRepository.findById(query.id());
    }

    @Override
    public List<Benefit> handle(GetAllBenefitsQuery query) {
        return benefitRepository.findAll();
    }
}
