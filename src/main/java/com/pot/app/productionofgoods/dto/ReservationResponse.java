package com.pot.app.productionofgoods.dto;

import java.util.List;

public record ReservationResponse(
        String externalUserId,
        String reservationNumber,
        List<ReservationItemDto> createReservationItems,
        List<ReservationItemDto> notCreateReservationItems
) {}
