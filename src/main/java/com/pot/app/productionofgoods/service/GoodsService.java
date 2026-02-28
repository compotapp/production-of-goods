package com.pot.app.productionofgoods.service;

import com.pot.app.productionofgoods.dto.GoodsDto;
import com.pot.app.productionofgoods.entity.Goods;
import com.pot.app.productionofgoods.entity.UpdateResultGoods;

import java.util.List;
import java.util.Set;

public interface GoodsService {

    GoodsDto crete(GoodsDto dto);

    List<Goods> findAllByNameIn(Set<String> names);

    List<UpdateResultGoods> updateResultAll(String[] names, Integer[] quantities);
}
