package com.pot.app.productionofgoods.repository.jpa;

import com.pot.app.productionofgoods.entity.NumberSequence;
import com.pot.app.productionofgoods.entity.NumberSequence.SequenceType;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static jakarta.persistence.LockModeType.PESSIMISTIC_WRITE;

@Repository
public interface NumberSequenceRepository extends GeneralRepository<NumberSequence, SequenceType>{

    @Lock(PESSIMISTIC_WRITE)
    @Query("SELECT ns FROM NumberSequence ns WHERE ns.type = :type")
    Optional<NumberSequence> findByTypeWithPessimisticLock(SequenceType type);
}
