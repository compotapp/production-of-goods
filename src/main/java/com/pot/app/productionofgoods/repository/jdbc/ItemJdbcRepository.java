package com.pot.app.productionofgoods.repository.jdbc;

import com.pot.app.productionofgoods.entity.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Transactional
public class ItemJdbcRepository {

    private static final Logger log = LoggerFactory.getLogger(ItemJdbcRepository.class);
    private static final int BATCH_SIZE = 2;

    private final JdbcTemplate jdbcTemplate;

    public ItemJdbcRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void updateAllByName(List<Item> items) {
        String sql = "UPDATE item SET quantity = quantity + ? WHERE name = ?";

        jdbcTemplate.batchUpdate(sql, items, BATCH_SIZE, ((ps, item) -> {
            ps.setInt(1, item.getQuantity());
            ps.setString(2, item.getName());
        }));
    }

//    public BatchUpdateResult updateWithDetails(List<Entity> entities) {
//        String sql = "UPDATE entity SET quantity = quantity + ? WHERE name = ?";
//
//        List<Entity> failed = new ArrayList<>();
//        List<Entity> succeeded = new ArrayList<>();
//
//        int[] results = jdbcTemplate.batchUpdate(sql, entities, BATCH_SIZE, (ps, entity) -> {
//            ps.setInt(1, entity.getQuantity());
//            ps.setString(2, entity.getName());
//        });
//
//        for (int i = 0; i < results.length; i++) {
//            if (results[i] > 0) {
//                succeeded.add(entities.get(i));
//            } else {
//                failed.add(entities.get(i));
//            }
//        }
//
//        return new BatchUpdateResult(succeeded, failed);
//    }
//
//    // Внутренний класс для результата
//    static class BatchUpdateResult {
//        private final List<Entity> succeeded;
//        private final List<Entity> failed;
//
//        // constructor, getters...
//        public BatchUpdateResult(List<Entity> succeeded, List<Entity> failed) {
//            this.succeeded = succeeded;
//            this.failed = failed;
//        }
//
//        public List<Entity> getSucceeded() { return succeeded; }
//        public List<Entity> getFailed() { return failed; }
//    }
}
