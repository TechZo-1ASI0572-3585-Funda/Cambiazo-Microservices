package com.cambiazo.donation.application.internal.queryservices;

import com.cambiazo.donation.domain.model.aggregates.Ong;
import com.cambiazo.donation.domain.model.entities.CategoryOng;
import com.cambiazo.donation.domain.model.queries.GetAllOngsQuery;
import com.cambiazo.donation.domain.model.queries.GetOngByIdQuery;
import com.cambiazo.donation.domain.model.queries.GetOngByLettersQuery;
import com.cambiazo.donation.domain.model.queries.GetOngsByCategoryOngIdQuery;
import com.cambiazo.donation.domain.services.OngQueryService;
import com.cambiazo.donation.infrastructure.persistence.jpa.CategoryOngRepository;
import com.cambiazo.donation.infrastructure.persistence.jpa.OngRepository;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class OngQueryServiceImpl implements OngQueryService {
    private final OngRepository ongRepository;
    private final CategoryOngRepository categoryOngRepository;

    public OngQueryServiceImpl(OngRepository ongRepository, CategoryOngRepository categoryOngRepository) {
        this.ongRepository = ongRepository;
        this.categoryOngRepository = categoryOngRepository;
    }

    @Override
    public Optional<Ong> handle(GetOngByIdQuery query) {
        return ongRepository.findById(query.Id());
    }

    @Override
    public List<Ong>handle(GetAllOngsQuery query) {
        return ongRepository.findAll();
    }

    @Override
    public List<Ong> handle(GetOngsByCategoryOngIdQuery query) {
        CategoryOng categoryOng = categoryOngRepository.findById(query.categoryOngId())
                .orElseThrow(() -> new IllegalArgumentException("CategoryOng with id " + query.categoryOngId() + " not found"));
        return ongRepository.findByCategoryOngId(categoryOng);
    }

    @Override
    public List<Ong>handle(GetOngByLettersQuery query) {
        return ongRepository.findByNameContaining(query.letters());
    }

    @Override
    public Optional<Ong> getOngWithRelations(Long id) {
        Optional<Ong> ongBase = ongRepository.findOneWithRelations(id);
        if (ongBase.isPresent()) {
            Ong ong = ongBase.get();
            // Inicializar las colecciones
            Hibernate.initialize(ong.getProjects());
            Hibernate.initialize(ong.getAccountNumbers());
            Hibernate.initialize(ong.getSocialNetworks());
            return Optional.of(ong);
        }
        return Optional.empty();
    }



}
