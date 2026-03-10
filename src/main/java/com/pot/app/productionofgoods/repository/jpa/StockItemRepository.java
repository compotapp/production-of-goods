package com.pot.app.productionofgoods.repository.jpa;

import com.pot.app.productionofgoods.entity.StockItem;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface StockItemRepository extends GeneralRepository<StockItem, Long> {

    @Modifying
    @Query(value = "SELECT * FROM update_stock_items(?1, ?2)", nativeQuery = true)
    Set<String> updateStockItems(String[] numbers, Integer[] quantities);

    @Query(value = """
            SELECT *
            FROM stock_items si
            WHERE si.quantity < si.min_stock
              AND NOT EXISTS(SELECT 1
                             FROM tasks t
                             WHERE t.product_number = si.product_number
                               AND t.type = 'PRODUCTION_MIN_STOCK'
                               AND t.status != 'COMPLETE')
            LIMIT 1 FOR UPDATE OF si SKIP LOCKED;""", nativeQuery = true)
    Optional<StockItem> findFirstByNoTaskMinStock();

    @Modifying
    @Query("UPDATE StockItem SET quantity = quantity + :quantity WHERE product.number = :productNumber")
    void addQuantity(String productNumber, int quantity);
}
