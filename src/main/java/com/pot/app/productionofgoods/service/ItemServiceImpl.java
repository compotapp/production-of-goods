package com.pot.app.productionofgoods.service;

import com.pot.app.productionofgoods.dto.ItemDto;
import com.pot.app.productionofgoods.entity.Item;
import com.pot.app.productionofgoods.entity.ItemReservationResult;
import com.pot.app.productionofgoods.mapping.ItemMapping;
import com.pot.app.productionofgoods.repository.jdbc.ItemJdbcRepository;
import com.pot.app.productionofgoods.repository.jpa.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

import static com.pot.app.productionofgoods.mapping.ItemMapping.toDto;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository repository;
    private final ItemJdbcRepository jdbcRepository;

    public ItemServiceImpl(ItemRepository repository, ItemJdbcRepository jdbcRepository) {
        this.repository = repository;
        this.jdbcRepository = jdbcRepository;
    }

    @Override
    @Transactional
    public ItemDto crete(ItemDto dto) {
        Item entity = ItemMapping.toEntity(dto);
        return toDto(repository.save(entity));
    }

    @Override
    @Transactional//работало и без них
    public List<Item> findAllByNameIn(Set<String> names) {
        return repository.findAllByNameIn(names);
    }

    @Override
    @Transactional//работало и без них
    public List<ItemReservationResult> reserveItems(String[] names, Integer[] quantities) {
        return repository.reserveItems(names, quantities);
    }

    @Override
    public void updateAllByName(List<Item> items) {
        jdbcRepository.updateAllByName(items);
    }
}
