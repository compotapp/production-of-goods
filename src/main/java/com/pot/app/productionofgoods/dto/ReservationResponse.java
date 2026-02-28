package com.pot.app.productionofgoods.dto;

import java.util.List;

public record ReservationResponse(
        String ownerId,
        String reservationNumber,
        List<ReservationItemDto> confirmedItems,
        List<ReservationItemDto> rejectedItems
) {}
