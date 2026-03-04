package com.pot.app.productionofgoods.controller;

import com.pot.app.productionofgoods.dto.OrderDto;
import com.pot.app.productionofgoods.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @PostMapping("/create")
    public OrderDto create(@RequestBody OrderDto dto) {
        return service.create(dto);
    }

    @PostMapping("/reservation")
    public OrderDto reservation(@RequestBody OrderDto dto) {
        return service.reservation(dto);
    }

    @PostMapping("/cancel-reservation")
    public OrderDto canselReservation(@RequestBody OrderDto dto) {
        return service.canceled(dto.number());
    }
}
