package com.pot.app.productionofgoods.repository;

import com.pot.app.productionofgoods.entity.Goods;
import com.pot.app.productionofgoods.entity.UpdateResultGoods;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface GoodsRepository extends GeneralRepository<Goods, Long>{

    Set<Goods> findAllByNameIn(Set<String> names);

    @Query(value = "SELECT * FROM reservation_of_goods(?1, ?2)", nativeQuery = true)
    Set<UpdateResultGoods> goodsTest(String[] names, Integer[] quantities);
}
