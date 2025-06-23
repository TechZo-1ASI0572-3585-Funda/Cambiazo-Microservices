package com.cambiazo.donation.domain.services;



import com.cambiazo.donation.domain.model.aggregates.Ong;
import com.cambiazo.donation.domain.model.queries.GetAllOngsQuery;
import com.cambiazo.donation.domain.model.queries.GetOngByIdQuery;
import com.cambiazo.donation.domain.model.queries.GetOngByLettersQuery;
import com.cambiazo.donation.domain.model.queries.GetOngsByCategoryOngIdQuery;

import java.util.List;
import java.util.Optional;

public interface OngQueryService {
    Optional<Ong> handle(GetOngByIdQuery query);
    List<Ong> handle(GetAllOngsQuery query);

    List<Ong>handle(GetOngsByCategoryOngIdQuery query);

    List<Ong>handle(GetOngByLettersQuery query);

    Optional<Ong> getOngWithRelations(Long id);

}
