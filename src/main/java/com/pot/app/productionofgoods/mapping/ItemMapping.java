package com.pot.app.productionofgoods.mapping;

import com.pot.app.productionofgoods.dto.ItemDto;
import com.pot.app.productionofgoods.entity.Item;

public class ItemMapping {

    public static Item toEntity(ItemDto dto) {
        return Item.builder()
                .id(dto.id())
                .name(dto.name())
                .quantity(dto.quantity())
                .category(dto.category())
                .build();
    }

    public static ItemDto toDto(Item entity) {
        return new ItemDto(
                entity.getId(),
                entity.getName(),
                entity.getQuantity(),
                entity.getCategory()
        );
    }
}
