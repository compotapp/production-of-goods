package com.pot.app.productionofgoods.repository;

import com.pot.app.productionofgoods.entity.Item;
import com.pot.app.productionofgoods.entity.ItemReservationResult;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface ItemRepository extends GeneralRepository<Item, Long>{

    List<Item> findAllByNameIn(Collection<String> names);

    @Query(value = "SELECT * FROM reserve_items(?1, ?2)", nativeQuery = true)
    List<ItemReservationResult> reserveItems(String[] names, Integer[] quantities);
}
