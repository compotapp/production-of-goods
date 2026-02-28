package com.pot.app.productionofgoods.mapping;

import com.pot.app.productionofgoods.dto.GoodsDto;
import com.pot.app.productionofgoods.entity.Goods;

public class GoodsMapping {

    public static Goods toEntity(GoodsDto dto) {
        return Goods.builder()
                .id(dto.id())
                .name(dto.name())
                .quantity(dto.quantity())
                .category(dto.category())
                .build();
    }

    public static GoodsDto toDto(Goods entity) {
        return new GoodsDto(
                entity.getId(),
                entity.getName(),
                entity.getQuantity(),
                entity.getCategory()
        );
    }
}
