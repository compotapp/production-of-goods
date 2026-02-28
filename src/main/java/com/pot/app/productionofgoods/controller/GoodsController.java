package com.pot.app.productionofgoods.controller;

import com.pot.app.productionofgoods.dto.GoodsDto;
import com.pot.app.productionofgoods.service.GoodsService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/goods")
public class GoodsController {
    private static final Logger log = LoggerFactory.getLogger(GoodsController.class);

    private final GoodsService service;

    public GoodsController(GoodsService service) {
        this.service = service;
    }

    @PostMapping
    public GoodsDto create(@Valid @RequestBody GoodsDto dto) {
        return service.crete(dto);
    }
}
