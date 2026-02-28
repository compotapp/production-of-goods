package com.pot.app.productionofgoods.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ReservationConfirmation(

        @NotBlank(message = "Reservation number cannot be empty")
        String reservationNumber,

        @NotNull(message = "The confirmation can't be null")
        Boolean isConfirm
) {
}