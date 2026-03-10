package com.pot.app.productionofgoods.repository.jpa;

import com.pot.app.productionofgoods.entity.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends GeneralRepository<Order, Long> {

    Order findByNumber(String number);

    @Query(value = """
            SELECT *
            FROM orders o
            WHERE o.status = 'RESERVED'
              AND NOT EXISTS(SELECT 1
                             FROM tasks t
                             WHERE t.order_number = o.number)
            LIMIT 1 FOR UPDATE OF o SKIP LOCKED;""", nativeQuery = true)
    Optional<Order> findFirstByNoTaskReserved();
}
