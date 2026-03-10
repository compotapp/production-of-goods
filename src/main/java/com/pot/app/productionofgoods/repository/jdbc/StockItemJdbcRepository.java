package com.pot.app.productionofgoods.repository.jdbc;

import com.pot.app.productionofgoods.entity.StockItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Transactional
public class StockItemJdbcRepository {

    private static final Logger log = LoggerFactory.getLogger(StockItemJdbcRepository.class);
    private static final int BATCH_SIZE = 2;

    private final JdbcTemplate jdbcTemplate;

    public StockItemJdbcRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void updateAllByNumbers(List<StockItem> items) {
        String sql = "UPDATE stock_items SET quantity = quantity + ? WHERE product_number = ?";

        jdbcTemplate.batchUpdate(sql, items, BATCH_SIZE, ((ps, item) -> {
            ps.setInt(1, item.getQuantity());
            ps.setString(2, item.getProduct().getNumber());
        }));
    }
}
