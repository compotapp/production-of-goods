package com.pot.app.productionofgoods.mapping;

import com.pot.app.productionofgoods.dto.OrderDto;
import com.pot.app.productionofgoods.dto.StockItemDto;
import com.pot.app.productionofgoods.entity.Order;
import com.pot.app.productionofgoods.entity.Product;
import com.pot.app.productionofgoods.entity.StockItem;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.pot.app.productionofgoods.enums.OrderItemStatus.OUT_OF_STOCK;

public class StockItemMapper {

    public static StockItem toEntity(StockItemDto dto, Product product) {
        return StockItem.builder()
                .product(product)
                .quantity(dto.quantity())
                .minStock(dto.minStock())
                .maxStock(dto.maxStock())
                .build();
    }

    public static List<StockItem> toEntity(List<StockItemDto> dtos, List<Product> products) {
        Map<String, Product> productMap = products.stream()
                .collect(Collectors.toMap(Product::getName, product -> product,
                        (existing, replacement) -> existing));//Опционально, если ключ уже есть в map можно выбрать оставить старое значение или добавить новое
        return dtos.stream()
                .map(dto -> toEntity(dto, productMap.get(dto.productName())))
                .toList();
    }

    public static StockItemDto toDto(StockItem stockItem) {
        return new StockItemDto(
                stockItem.getProduct().getName(),
                stockItem.getProduct().getNumber(),
                stockItem.getQuantity(),
                stockItem.getMinStock(),
                stockItem.getMaxStock()
        );
    }

    public static List<StockItemDto> toDto(List<StockItem> stockItems) {
        return stockItems.stream()
                .map(StockItemMapper::toDto)
                .toList();
    }

    public static List<StockItemDto> toReservationDto(OrderDto orderDto) {
        return orderDto.orderItemDtos().stream()
                .map(dto -> new StockItemDto(
                        dto.productName(),
                        dto.productNumber(),
                        dto.quantity(),
                        null,
                        null))
                .toList();
    }

    public static List<StockItemDto> toCancelReservationDto(Order order) {
        return order.getItems().stream()
                .filter(item -> item.getStatus() != OUT_OF_STOCK)
                .map(item -> new StockItemDto(
                        item.getProduct().getName(),
                        item.getProduct().getNumber(),
                        item.getQuantity(),
                        null,
                        null))
                .toList();
    }

    public static List<String> getNames(List<StockItemDto> dtos) {
        return dtos.stream()
                .map(StockItemDto::productName)
                .toList();
    }

    public static DataForUpdate getDataForUpdate(List<StockItemDto> dtos, boolean add) {
        int size = dtos.size();
        String[] numbers = new String[size];
        Integer[] quantities = new Integer[size];
        for (int i = 0; i < size; i++) {
            StockItemDto dto = dtos.get(i);
            numbers[i] = dto.productNumber();
            quantities[i] = add ? dto.quantity() : Math.negateExact(dto.quantity());
        }
        return new DataForUpdate(numbers, quantities);
    }

    public record DataForUpdate(
            String[] numbers,
            Integer[] quantities
    ) {}
}
