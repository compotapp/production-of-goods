package com.pot.app.productionofgoods.repository.jpa;

import com.pot.app.productionofgoods.entity.StockItem;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface StockItemRepository extends GeneralRepository<StockItem, Long> {

    @Modifying
    @Query(value = "SELECT * FROM update_stock_items(?1, ?2)", nativeQuery = true)
    Set<String> updateStockItems(String[] numbers, Integer[] quantities);
}
