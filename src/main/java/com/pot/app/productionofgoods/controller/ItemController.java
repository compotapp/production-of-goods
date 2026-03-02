package com.pot.app.productionofgoods.controller;

import com.pot.app.productionofgoods.dto.ItemDto;
import com.pot.app.productionofgoods.entity.Item;
import com.pot.app.productionofgoods.service.ItemService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.pot.app.productionofgoods.enums.ItemCategory.FRUIT;
import static com.pot.app.productionofgoods.enums.ItemCategory.VEGETABLE;

@RestController
@RequestMapping("v1/item")
public class ItemController {
    private static final Logger log = LoggerFactory.getLogger(ItemController.class);

    private final ItemService service;

    public ItemController(ItemService service) {
        this.service = service;
    }

    @PostMapping
    public ItemDto create(@Valid @RequestBody ItemDto dto) {
        return service.crete(dto);
    }

    @PutMapping
    public void updateAllByName() {
        List<Item> items = List.of(
                new Item("pear", 10, FRUIT),
                new Item("apple", 10, FRUIT),
                new Item("peach", 10, FRUIT),
                new Item("tomato", 10, VEGETABLE),
                new Item("cucumber", 10, VEGETABLE),
                new Item("carrot", 10, VEGETABLE),
                new Item("carrot", 10, VEGETABLE),
                new Item("carrot", 10, VEGETABLE),
                new Item("carrot", 10, VEGETABLE),
                new Item("carrot", 10, VEGETABLE)
        );
        service.updateAllByName(items);
    }
}
