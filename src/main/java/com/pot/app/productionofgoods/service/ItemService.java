package com.pot.app.productionofgoods.service;

import com.pot.app.productionofgoods.dto.ItemDto;
import com.pot.app.productionofgoods.entity.Item;
import com.pot.app.productionofgoods.entity.ItemReservationResult;

import java.util.List;
import java.util.Set;

public interface ItemService {

    ItemDto crete(ItemDto dto);

    List<Item> findAllByNameIn(Set<String> names);

    List<ItemReservationResult> reserveItems(String[] names, Integer[] quantities);
}
