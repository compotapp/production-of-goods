package com.pot.app.productionofgoods.service;

import com.pot.app.productionofgoods.dto.ReservationConfirmation;
import com.pot.app.productionofgoods.dto.ReservationRequest;
import com.pot.app.productionofgoods.dto.ReservationResponse;
import com.pot.app.productionofgoods.entity.Item;
import com.pot.app.productionofgoods.entity.ItemReservationResult;
import com.pot.app.productionofgoods.entity.Reservation;
import com.pot.app.productionofgoods.mapping.ReservationMapping;
import com.pot.app.productionofgoods.repository.ReservationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.pot.app.productionofgoods.enums.ReservationStatus.*;
import static com.pot.app.productionofgoods.mapping.ReservationMapping.*;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository repository;
    private final ItemService itemService;

    public ReservationServiceImpl(ReservationRepository repository, ItemService itemService) {
        this.repository = repository;
        this.itemService = itemService;
    }

    @Override
    @Transactional
    public ReservationResponse createReservation(ReservationRequest request) {
        List<ItemReservationResult> reservationResults = reserveItems(request);
        List<Item> items = itemService.findAllByNameIn(getNames(request));
        List<Reservation> reservations = repository.saveAll(toEntity(request, items, reservationResults));
        return ReservationMapping.toDto(reservationResults, reservations);
    }

    @Override
    @Transactional
    public void confirmReservation(ReservationConfirmation confirmation) {
        if (confirmation.isConfirm()) {
            activateReservation(confirmation.reservationNumber());
        } else {
            cancelReservation(confirmation.reservationNumber());
        }
    }

    private List<ItemReservationResult> reserveItems(ReservationRequest request) {
        return itemService.reserveItems(
                getItemNames(request),
                getItemQuantities(request)
        );
    }

    private void activateReservation(String reservationNumber) {
        repository.updateStatus(reservationNumber, PENDING, ACTIVE);
    }

    private void cancelReservation(String reservationNumber) {
        repository.updateStatus(reservationNumber, CANCELLED);
    }
}
