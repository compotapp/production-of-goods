package com.pot.app.productionofgoods.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

import static com.pot.app.productionofgoods.enums.ItemCategory.FRUIT;
import static com.pot.app.productionofgoods.enums.ItemCategory.VEGETABLE;

@Getter
public enum ItemName{

    //FRUIT
    APPLE(FRUIT),
    PEACH(FRUIT),
    PEAR (FRUIT),

    //VEGETABLE
    CARROT  (VEGETABLE),
    CUCUMBER(VEGETABLE),
    TOMATO  (VEGETABLE);

    private final ItemCategory category;

    ItemName(ItemCategory category) {
        this.category = category;
    }

    public static List<ItemName> findAllByCategory(ItemCategory category) {
        return Arrays.stream(values())
                .filter(itemName -> itemName.category == category)
                .toList();
    }
}
