package com.pot.app.productionofgoods.controller;

import com.pot.app.productionofgoods.dto.ReservationConfirmation;
import com.pot.app.productionofgoods.dto.ReservationRequest;
import com.pot.app.productionofgoods.dto.ReservationResponse;
import com.pot.app.productionofgoods.service.ReservationService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/reservation")
public class ReservationController {

    private final ReservationService service;

    public ReservationController(ReservationService service) {
        this.service = service;
    }

    @PostMapping
    public ReservationResponse createReservation(@Valid @RequestBody ReservationRequest request) {
        return service.createReservation(request);
    }

    @PutMapping
    public void confirmReservation(@Valid @RequestBody ReservationConfirmation dto) {
        service.confirmReservation(dto);
    }
}
