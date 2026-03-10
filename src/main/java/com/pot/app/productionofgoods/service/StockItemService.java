package com.pot.app.productionofgoods.service;

import com.pot.app.productionofgoods.dto.StockItemDto;
import com.pot.app.productionofgoods.entity.StockItem;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface StockItemService {

    StockItemDto save(StockItemDto dto);

    List<StockItemDto> save(List<StockItemDto> dtos);

    Set<String> cancelReservation(List<StockItemDto> dtos);

    Set<String> reservation(List<StockItemDto> dtos);

    Optional<StockItem> findByNoTaskMinStock();

    void addQuantity(String productNumber, int quantity);
}
