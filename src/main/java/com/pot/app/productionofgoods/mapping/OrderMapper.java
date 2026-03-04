package com.pot.app.productionofgoods.mapping;

import com.pot.app.productionofgoods.dto.OrderDto;
import com.pot.app.productionofgoods.entity.Order;
import com.pot.app.productionofgoods.entity.OrderItem;
import com.pot.app.productionofgoods.entity.Product;
import com.pot.app.productionofgoods.enums.OrderItemStatus;
import com.pot.app.productionofgoods.enums.OrderStatus;

import java.util.List;
import java.util.Set;

import static com.pot.app.productionofgoods.enums.OrderItemStatus.OUT_OF_STOCK;
import static com.pot.app.productionofgoods.enums.OrderItemStatus.RESERVED;
import static com.pot.app.productionofgoods.enums.OrderStatus.*;

public class OrderMapper {

    public static List<String> getProductNames(OrderDto dto) {
        return dto.orderItemDtos().stream()
                .map(OrderDto.OrderItemDto::productName)
                .toList();
    }

    public static Order toEntity(OrderDto dto, List<Product> products, String ownerNumber, String orderNumber) {
        Order order = Order.builder()
                .owner(ownerNumber)
                .number(orderNumber)
                .status(ACTIVE)
                .build();
        List<OrderItem> orderItems = OrderItemMapper.toEntity(dto.orderItemDtos(), order, products);
        order.setItems(orderItems);
        return order;
    }

    public static OrderDto toDto(Order order) {
        return new OrderDto(
                order.getOwner(),
                order.getNumber(),
                order.getStatus().getTitle(),
                OrderItemMapper.toDto(order.getItems())
        );
    }

    public static void setReservedStatus(Order order, Set<String> productNumbers) {
        boolean allReserved = order.getItems().size() == productNumbers.size();
        order.setStatus(allReserved ? OrderStatus.RESERVED : PENDING);
        order.getItems()
                .forEach(item ->
                        item.setStatus(productNumbers.contains(item.getProduct().getNumber()) ? RESERVED : OUT_OF_STOCK));
    }

    public static void setCanceledStatus(Order order) {
        order.setStatus(CANCELLED);
        order.getItems()
                .stream().filter(item -> item.getStatus() != OUT_OF_STOCK)
                .forEach(item -> item.setStatus(OrderItemStatus.CANCELLED));
    }
}
