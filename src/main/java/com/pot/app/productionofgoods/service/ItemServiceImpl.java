package com.pot.app.productionofgoods.service;

import com.pot.app.productionofgoods.dto.ItemDto;
import com.pot.app.productionofgoods.entity.Item;
import com.pot.app.productionofgoods.entity.ItemReservationResult;
import com.pot.app.productionofgoods.mapping.ItemMapping;
import com.pot.app.productionofgoods.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

import static com.pot.app.productionofgoods.mapping.ItemMapping.toDto;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository repository;

    public ItemServiceImpl(ItemRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public ItemDto crete(ItemDto dto) {
        Item entity = ItemMapping.toEntity(dto);
        return toDto(repository.save(entity));
    }

    @Override
    public List<Item> findAllByNameIn(Set<String> names) {
        return repository.findAllByNameIn(names);
    }

    @Override
    public List<ItemReservationResult> reserveItems(String[] names, Integer[] quantities) {
        return repository.reserveItems(names, quantities);
    }
}
