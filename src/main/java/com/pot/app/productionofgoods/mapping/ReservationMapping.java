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

import static com.pot.app.productionofgoods.enums.ReservationStatus.*;
import static com.pot.app.productionofgoods.util.RandomUUID.generateUUID;

public class ReservationMapping {

    public static List<Reservation> toEntity(
            ReservationRequest dto, List<Goods> goods, List<UpdateResultGoods> updateResultGoods) {
        String reservationNumber = generateUUID();
        Map<String, Goods> goodsMap = goods.stream()
                .collect(Collectors.toMap(Goods::getName, goods1 -> goods1));
        boolean statusIsActive = statusIsActive(updateResultGoods);
        return updateResultGoods.stream()
                .map(updateResult -> Reservation.builder()
                        .reservationNumber(reservationNumber)
                        .reservationOwner(dto.externalUserId())
                        .quantity(updateResult.getReservationQuantity())
                        .status(statusIsActive ? ACTIVE : updateResult.getGoodsId() != null ? PENDING : OUT_OF_STOCK)
                        .goods(goodsMap.get(updateResult.getGoodsName()))
                        .build()
                ).collect(Collectors.toUnmodifiableList());
    }

    public static ReservationResponse toResponse(
            List<UpdateResultGoods> updateResultGoods,
            List<Reservation> reservations
    ) {
        Reservation reservation = reservations.get(0);
        return new ReservationResponse(
                reservation.getReservationOwner(),
                reservation.getReservationNumber(),
                updateResultGoods.stream()
                        .filter(updateResult -> updateResult.getGoodsId() != null)
                        .map(updateResult -> new ReservationItemDto(
                                updateResult.getGoodsName(),
                                updateResult.getReservationQuantity()))
                        .toList(),
                updateResultGoods.stream()
                        .filter(updateResult -> updateResult.getGoodsId() == null)
                        .map(updateResult -> new ReservationItemDto(
                                updateResult.getGoodsName(),
                                updateResult.getReservationQuantity()))
                        .toList()
                );
    }

    public static Set<String> getNames(ReservationRequest dto) {
        return dto.reservationItemsDto().stream()
                .map(ReservationItemDto::name)
                .collect(Collectors.toSet());
    }

    public static String[] getArrayNames(ReservationRequest dto) {
        return dto.reservationItemsDto().stream()
                .map(ReservationItemDto::name)
                .toArray(String[]::new);
    }

    public static Integer[] getArrayQuantities(ReservationRequest dto) {
        return dto.reservationItemsDto().stream()
                .map(ReservationItemDto::quantity)
                .toArray(Integer[]::new);
    }

    private static Boolean statusIsActive(List<UpdateResultGoods> updateResultGoods) {
        return updateResultGoods.stream()
                .filter(updateResult -> updateResult.getGoodsId() == null)
                .findFirst()
                .isEmpty();
    }
}
