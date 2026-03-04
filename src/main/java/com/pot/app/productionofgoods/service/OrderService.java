package com.pot.app.productionofgoods.service;

import com.pot.app.productionofgoods.dto.OrderDto;

public interface OrderService {

    OrderDto create(OrderDto dto);

    OrderDto reservation(OrderDto dto);

    OrderDto canceled(String number);
}
