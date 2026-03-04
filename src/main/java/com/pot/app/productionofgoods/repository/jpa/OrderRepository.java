package com.pot.app.productionofgoods.repository.jpa;

import com.pot.app.productionofgoods.entity.Order;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends GeneralRepository<Order, Long> {

    Order findByNumber(String number);
}
