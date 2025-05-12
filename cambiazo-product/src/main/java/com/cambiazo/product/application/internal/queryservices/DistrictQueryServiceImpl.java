package com.cambiazo.product.application.internal.queryservices;


import com.cambiazo.product.domain.model.entities.District;
import com.cambiazo.product.domain.model.queries.GetAllDistrictsQuery;
import com.cambiazo.product.domain.model.queries.GetDistrictByIdQuery;
import com.cambiazo.product.domain.services.IDistrictQueryService;
import com.cambiazo.product.infrastructure.persistence.jpa.IDistrictRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DistrictQueryServiceImpl implements IDistrictQueryService {

    private final IDistrictRepository districtRepository;

    public DistrictQueryServiceImpl(IDistrictRepository districtRepository){
        this.districtRepository=districtRepository;
    }

    @Override
    public Optional<District>handle(GetDistrictByIdQuery query){
        return this.districtRepository.findById(query.id());
    }

    @Override
    public List<District>handle(GetAllDistrictsQuery query){
        return this.districtRepository.findAll();
    }
}
