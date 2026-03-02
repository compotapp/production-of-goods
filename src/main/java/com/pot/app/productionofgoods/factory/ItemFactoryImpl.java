package com.pot.app.productionofgoods.factory;

import com.pot.app.productionofgoods.entity.Item;
import com.pot.app.productionofgoods.enums.ItemName;
import org.springframework.stereotype.Service;

@Service
public class ItemFactoryImpl implements ItemFactory {

    @Override
    public Item create(ItemName itemName) {
        return Item.builder()
                .name(itemName.name().toLowerCase())
                .quantity(1)
                .category(itemName.getCategory())
                .build();
    }
}
