package com.pot.app.productionofgoods.service;

import com.pot.app.productionofgoods.dto.ReservationRequest;
import com.pot.app.productionofgoods.dto.ReservationResponse;

public interface ReservationService {

    ReservationResponse reservation(ReservationRequest dto);
}
