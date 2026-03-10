package com.pot.app.productionofgoods.service;

import com.pot.app.productionofgoods.dto.OrderDto;
import com.pot.app.productionofgoods.entity.Order;

import java.util.Optional;

public interface OrderService {

    Order save(Order order);

    OrderDto create(OrderDto dto);

    OrderDto reservation(OrderDto dto);

    OrderDto canceled(String number);

    Optional<Order> findByNoTaskReserved();
}
