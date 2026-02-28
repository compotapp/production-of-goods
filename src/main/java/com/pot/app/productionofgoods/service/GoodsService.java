package com.pot.app.productionofgoods.service;

import com.pot.app.productionofgoods.dto.GoodsDto;
import com.pot.app.productionofgoods.entity.Goods;
import com.pot.app.productionofgoods.entity.UpdateResultGoods;

import java.util.List;
import java.util.Set;

public interface GoodsService {

    GoodsDto crete(GoodsDto dto);

    List<Goods> updateAll(Set<Goods> goodsSet);

    Set<Goods> findAllByNameIn(Set<String> names);

    Set<UpdateResultGoods> updateAll(String[] ids, Integer[] quantities);
}
