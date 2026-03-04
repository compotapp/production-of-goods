package com.pot.app.productionofgoods.service;

import com.pot.app.productionofgoods.dto.StockItemDto;

import java.util.List;
import java.util.Set;

public interface StockItemService {

    StockItemDto create(StockItemDto dto);

    List<StockItemDto> create(List<StockItemDto> dtos);

    Set<String> cancelReservation(List<StockItemDto> dtos);

    Set<String> reservation(List<StockItemDto> dtos);
}
