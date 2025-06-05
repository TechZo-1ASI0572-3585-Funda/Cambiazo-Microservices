package com.cambiazo.exchange.infrastructure.persistence.jpa;

import com.cambiazo.exchange.domain.model.dtos.ProductDto;
import com.cambiazo.exchange.domain.model.dtos.UserDto;
import com.cambiazo.exchange.domain.model.entities.Exchange;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IExchangeRepository extends JpaRepository<Exchange, Long> {

    Exchange findExchangeByProductOwnIdAndProductChangeId(Long productOwnId, Long productChangeId);

//    List<Exchange>findAllExchangesByProductOwnId_UserId(UserDto userOwnId);
//
//    List<Exchange>findAllExchangesByProductChangeId_UserId(UserDto userChangeId);

    List<Exchange>findAllExchangesByUserOwnId(Long userOwnId);

    List<Exchange>findAllExchangesByUserChangeId(Long userChangeId);

    @Query("SELECT e FROM Exchange e WHERE e.productOwnId = :productId OR e.productChangeId = :productId AND e.status = 'Pendiente'")
    List<Exchange>findAllExchangesByProductOwnIdOrProductChangeId(@Param("productId") ProductDto productId);


    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Transactional
    @Query("""
       UPDATE Exchange e
          SET e.status = 'Rechazado'
        WHERE e.productOwnId = :productId
          AND e.status = 'Pendiente'
          AND e.id <> :exchangeId
       """)
    int updateExchangeStatusToRejectedByProductOwnExcept(@Param("productId") Long productId,
                                                         @Param("exchangeId") Long exchangeId);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Transactional
    @Query("""
       UPDATE Exchange e
          SET e.status = 'Rechazado'
        WHERE e.productChangeId = :productId
          AND e.status = 'Pendiente'
          AND e.id <> :exchangeId
       """)
    int updateExchangeStatusToRejectedByProductChangeExcept(@Param("productId") Long productId,
                                                            @Param("exchangeId") Long exchangeId);

}
