package com.pot.app.productionofgoods.dto;

import com.pot.app.productionofgoods.enums.ItemCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ItemDto(
        Long id,

        @NotBlank(message = "Name cannot be empty")
        String name,

        @Positive(message = "The item quantity must be greater than zero")
        Integer quantity,

        @NotNull(message = "The item category must be defined")
        ItemCategory category
) {
}
