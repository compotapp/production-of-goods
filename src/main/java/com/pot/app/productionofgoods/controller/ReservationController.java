package com.pot.app.productionofgoods.controller;

import com.pot.app.productionofgoods.dto.ReservationConfirmationDto;
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
    public ReservationResponse reservation(@Valid @RequestBody ReservationRequest request) {
        return service.reservation(request);
    }

    @PutMapping
    public void reservationConfirmation(@Valid @RequestBody ReservationConfirmationDto dto) {
        service.reservationConfirmation(dto);
    }
}
