package com.pot.app.productionofgoods.service;

import com.pot.app.productionofgoods.dto.ReservationConfirmationDto;
import com.pot.app.productionofgoods.dto.ReservationRequest;
import com.pot.app.productionofgoods.dto.ReservationResponse;
import com.pot.app.productionofgoods.repository.ReservationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.pot.app.productionofgoods.enums.ReservationStatus.*;
import static com.pot.app.productionofgoods.mapping.ReservationMapping.*;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository repository;
    private final GoodsService goodsService;

    public ReservationServiceImpl(ReservationRepository repository, GoodsService goodsService) {
        this.repository = repository;
        this.goodsService = goodsService;
    }

    @Override
    @Transactional
    public ReservationResponse reservation(ReservationRequest dto) {
        var goods = goodsService.findAllByNameIn(getNames(dto));
        var updateResultGoods = goodsService.updateResultAll(getArrayNames(dto), getArrayQuantities(dto));
        var reservations = repository.saveAll(toEntity(dto, goods, updateResultGoods));
        return toResponse(updateResultGoods, reservations);
    }

    @Override
    @Transactional
    public void reservationConfirmation(ReservationConfirmationDto dto) {
        if (dto.isConfirmation()) {
            repository.updateStatus(dto.reservationNumber(), PENDING, ACTIVE);
        } else {
            repository.updateStatus(dto.reservationNumber(), CANCELLED);
        }
    }
}
