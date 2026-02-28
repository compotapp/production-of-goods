package com.pot.app.productionofgoods.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record ReservationItemDto(

        @NotBlank(message = "Name cannot be empty")
        String name,

        @Positive(message = "The goods quantity must be greater than zero")
        Integer quantity
) {}
