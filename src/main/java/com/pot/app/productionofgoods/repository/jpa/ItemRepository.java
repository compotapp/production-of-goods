package com.pot.app.productionofgoods.repository.jpa;

import com.pot.app.productionofgoods.entity.Item;
import com.pot.app.productionofgoods.entity.ItemReservationResult;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ItemRepository extends GeneralRepository<Item, Long>{

    List<Item> findAllByNameIn(Collection<String> names);

    @Query(value = "SELECT * FROM reserve_items(?1, ?2)", nativeQuery = true)
    List<ItemReservationResult> reserveItems(String[] names, Integer[] quantities);
}
