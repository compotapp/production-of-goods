package com.pot.app.productionofgoods.mapping;

import com.pot.app.productionofgoods.dto.ReservationItemDto;
import com.pot.app.productionofgoods.dto.ReservationRequest;
import com.pot.app.productionofgoods.dto.ReservationResponse;
import com.pot.app.productionofgoods.entity.Item;
import com.pot.app.productionofgoods.entity.Reservation;
import com.pot.app.productionofgoods.entity.ItemReservationResult;
import com.pot.app.productionofgoods.enums.ReservationStatus;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.pot.app.productionofgoods.enums.ReservationStatus.*;
import static com.pot.app.productionofgoods.util.RandomUUID.generateUUID;

public class ReservationMapping {

    public static List<Reservation> toEntity(
            ReservationRequest request,
            List<Item> items,
            List<ItemReservationResult> reservationResults) {

        String reservationNumber = generateUUID();
        Map<String, Item> itemByName = mapItemsByName(items);
        boolean allItemsAvailable = areAllItemsAvailable(reservationResults);

        return reservationResults.stream()
                .map(result -> Reservation.builder()
                        .number(reservationNumber)
                        .owner(request.ownerId())
                        .quantity(result.getReservationQuantity())
                        .status(determineStatus(result, allItemsAvailable))
                        .item(itemByName.get(result.getItemName()))
                        .build()
                ).collect(Collectors.toUnmodifiableList());
    }

    public static ReservationResponse toDto(
            List<ItemReservationResult> reservationResults,
            List<Reservation> reservations
    ) {
        
        Reservation firsReservation = reservations.get(0);
        
        return new ReservationResponse(
                firsReservation.getOwner(),
                firsReservation.getNumber(),
                toDto(reservationResults, true),
                toDto(reservationResults, false)
        );
    }

    public static Set<String> getNames(ReservationRequest dto) {
        return dto.reservationItemsDto().stream()
                .map(ReservationItemDto::name)
                .collect(Collectors.toSet());
    }

    public static String[] getItemNames(ReservationRequest dto) {
        return dto.reservationItemsDto().stream()
                .map(ReservationItemDto::name)
                .toArray(String[]::new);
    }

    public static Integer[] getItemQuantities(ReservationRequest dto) {
        return dto.reservationItemsDto().stream()
                .map(ReservationItemDto::quantity)
                .toArray(Integer[]::new);
    }

    private static Map<String, Item> mapItemsByName(List<Item> items) {
        return items.stream()
                .collect(Collectors.toMap(Item::getName, item -> item));
    }

    private static boolean areAllItemsAvailable(List<ItemReservationResult> reservationResults) {
        return reservationResults.stream()
                .noneMatch(result -> result.getItemId() == null);
    }

    private static ReservationStatus determineStatus(ItemReservationResult result, boolean allItemsAvailable) {
        if (result.getItemId() == null) {
            return OUT_OF_STOCK;
        }
        return allItemsAvailable ? ACTIVE : PENDING;
    }

    private static List<ReservationItemDto> toDto(List<ItemReservationResult> results, boolean available) {
        return results.stream()
                .filter(result -> (result.getItemId() != null) == available)
                .map(result -> new ReservationItemDto(
                        result.getItemName(),
                        result.getReservationQuantity()))
                .toList();
    }
}
