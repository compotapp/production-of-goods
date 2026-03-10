package com.pot.app.productionofgoods.repository;

import com.pot.app.productionofgoods.entity.OrderItem;
import com.pot.app.productionofgoods.repository.jpa.GeneralRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends GeneralRepository<OrderItem, Long> {
}
