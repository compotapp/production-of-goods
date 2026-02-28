package com.pot.app.productionofgoods.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ReservationRequest(

        @NotBlank(message = "Owner id cannot be empty")
        String ownerId,

        @NotNull(message = "The reservation can't be null")
        List<ReservationItemDto> reservationItemsDto
) {}
