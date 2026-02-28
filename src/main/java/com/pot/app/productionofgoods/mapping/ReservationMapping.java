package com.pot.app.productionofgoods.mapping;

import com.pot.app.productionofgoods.dto.ReservationItemDto;
import com.pot.app.productionofgoods.dto.ReservationRequest;
import com.pot.app.productionofgoods.dto.ReservationResponse;
import com.pot.app.productionofgoods.entity.Goods;
import com.pot.app.productionofgoods.entity.Reservation;
import com.pot.app.productionofgoods.entity.UpdateResultGoods;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.pot.app.productionofgoods.enums.ReservationStatus.PENDING;
import static com.pot.app.productionofgoods.util.RandomUUID.generateUUID;

public class ReservationMapping {

    public static List<Reservation> toEntity(ReservationRequest dto, Set<Goods> goods) {
        String reservationNumber = generateUUID();
        Map<String, Goods> goodsMap = goods.stream()
                .collect(Collectors.toMap(Goods::getName, goods1 -> goods1));
        return dto.reservationItemDtos().stream()
                .filter(reservationItemDto -> goodsMap.containsKey(reservationItemDto.name()))
                .map(reservationItemDto -> Reservation.builder()
                        .reservationNumber(reservationNumber)
                        .reservationOwner(dto.externalUserId())
                        .quantity(reservationItemDto.quantity())
                        .status(PENDING)
                        .goods(goodsMap.get(reservationItemDto.name()))
                        .build()
                ).collect(Collectors.toUnmodifiableList());
    }

    public static ReservationResponse toResponse(
            Set<UpdateResultGoods> updateResultGoods,
            List<Reservation> reservations
    ) {
        Reservation reservation = reservations.get(0);
        return new ReservationResponse(
                reservation.getReservationOwner(),
                reservation.getReservationNumber(),
                updateResultGoods.stream()
                        .filter(updateResult -> updateResult.getGoodsId() != null)
                        .map(updateResult -> new ReservationItemDto(
                                updateResult.getReservationName(),
                                updateResult.getReservationQuantity()))
                        .toList(),
                updateResultGoods.stream()
                        .filter(updateResult -> updateResult.getGoodsId() == null)
                        .map(updateResult -> new ReservationItemDto(
                                updateResult.getReservationName(),
                                updateResult.getReservationQuantity()))
                        .toList()
                );
    }

    public static Set<String> getNames(ReservationRequest dto) {
        return dto.reservationItemDtos().stream()
                .map(ReservationItemDto::name)
                .collect(Collectors.toSet());
    }

    public static String[] getArrayNames(ReservationRequest dto) {
        return dto.reservationItemDtos().stream()
                .map(ReservationItemDto::name)
                .toArray(String[]::new);
    }

    public static Integer[] getArrayQuantities(ReservationRequest dto) {
        return dto.reservationItemDtos().stream()
                .map(ReservationItemDto::quantity)
                .toArray(Integer[]::new);
    }
}
