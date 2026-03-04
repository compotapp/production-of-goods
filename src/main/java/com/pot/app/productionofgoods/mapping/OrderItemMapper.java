package com.pot.app.productionofgoods.mapping;

import com.pot.app.productionofgoods.dto.OrderDto.OrderItemDto;
import com.pot.app.productionofgoods.entity.Order;
import com.pot.app.productionofgoods.entity.OrderItem;
import com.pot.app.productionofgoods.entity.Product;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.pot.app.productionofgoods.enums.OrderItemStatus.ACTIVE;

public class OrderItemMapper {
    public static List<OrderItem> toEntity(List<OrderItemDto> dtos, Order order, List<Product> products) {
        Map<String, Product> productMap = products.stream()
                .collect(Collectors.toMap(Product::getNumber, product -> product));
        return dtos.stream()
                .map(dto -> OrderItem.builder()
                        .order(order)
                        .product(productMap.get(dto.productNumber()))
                        .quantity(dto.quantity())
                        .status(ACTIVE)
                        .build())
                .collect(Collectors.toUnmodifiableList());
    }

    public static List<OrderItemDto> toDto(List<OrderItem> items) {
        return items.stream()
                .map(item -> new OrderItemDto(
                        item.getProduct().getName(),
                        item.getProduct().getNumber(),
                        item.getQuantity(),
                        item.getStatus().getTitle()))
                .toList();
    }
}
