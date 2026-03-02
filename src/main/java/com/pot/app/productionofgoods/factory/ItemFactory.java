package com.pot.app.productionofgoods.factory;

import com.pot.app.productionofgoods.entity.Item;
import com.pot.app.productionofgoods.enums.ItemName;

public interface ItemFactory {

    Item create(ItemName name);
}
