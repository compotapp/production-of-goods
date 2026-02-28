package com.pot.app.productionofgoods.repository;

import com.pot.app.productionofgoods.entity.Goods;
import com.pot.app.productionofgoods.entity.UpdateResultGoods;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface GoodsRepository extends GeneralRepository<Goods, Long>{

    List<Goods> findAllByNameIn(Collection<String> names);

    @Query(value = "SELECT * FROM reservation_of_goods(?1, ?2)", nativeQuery = true)
    List<UpdateResultGoods> updateResult(String[] names, Integer[] quantities);
}
