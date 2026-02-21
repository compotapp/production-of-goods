package com.pot.app.productionofgoods.dto;

import com.pot.app.productionofgoods.enums.GoodsCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record GoodsDto(
        Long id,

        @NotBlank(message = "Name cannot be empty")
        String name,

        @Positive(message = "The goods amount must be greater than zero")
        Integer amount,

        @NotNull(message = "The goods category must be defined")
        GoodsCategory category
) {
}
