package com.pot.app.productionofgoods.service;

import com.pot.app.productionofgoods.dto.GoodsDto;
import com.pot.app.productionofgoods.entity.Goods;
import com.pot.app.productionofgoods.entity.UpdateResultGoods;
import com.pot.app.productionofgoods.mapping.GoodsMapping;
import com.pot.app.productionofgoods.repository.GoodsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

import static com.pot.app.productionofgoods.mapping.GoodsMapping.toDto;

@Service
public class GoodsServiceImpl implements GoodsService {

    private final GoodsRepository repository;

    public GoodsServiceImpl(GoodsRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public GoodsDto crete(GoodsDto dto) {
        Goods entity = GoodsMapping.toEntity(dto);
        return toDto(repository.save(entity));
    }

    @Override
    public List<Goods> updateAll(Set<Goods> goodsSet) {
        return repository.saveAll(goodsSet);
    }

    @Override
    public Set<Goods> findAllByNameIn(Set<String> names) {
        return repository.findAllByNameIn(names);
    }

    @Override
    public Set<UpdateResultGoods> updateAll(String[] names, Integer[] quantities) {
        return repository.goodsTest(names, quantities);
    }
}
