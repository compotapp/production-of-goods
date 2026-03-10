package com.pot.app.productionofgoods.service;

import com.pot.app.productionofgoods.dto.OrderDto;
import com.pot.app.productionofgoods.entity.Order;
import com.pot.app.productionofgoods.entity.Product;
import com.pot.app.productionofgoods.mapping.OrderMapper;
import com.pot.app.productionofgoods.mapping.StockItemMapper;
import com.pot.app.productionofgoods.repository.jpa.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.pot.app.productionofgoods.entity.NumberSequence.SequenceType.ORDER;
import static com.pot.app.productionofgoods.entity.NumberSequence.SequenceType.OWNER;
import static com.pot.app.productionofgoods.mapping.OrderMapper.*;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;
    private final ProductService productService;
    private final StockItemService stockItemService;
    private final NumberGeneratorServiceImpl numberGenerator;

    @Override
    @Transactional
    public Order save(Order order) {
        return repository.save(order);
    }

    @Override
    @Transactional
    public OrderDto create(OrderDto dto) {
        String ownerNumber = numberGenerator.generateNumber(OWNER);//todo потом убрать
        String orderNumber = numberGenerator.generateNumber(ORDER);
        List<String> names = getProductNames(dto);
        List<Product> products = productService.findAllByNameIn(names);
        Order order = repository.save(toEntity(dto, products, ownerNumber, orderNumber));
        return toDto(order);
    }

    @Override
    @Transactional
    public OrderDto reservation(OrderDto dto) {
        String ownerNumber = numberGenerator.generateNumber(OWNER);//todo потом убрать
        String orderNumber = numberGenerator.generateNumber(ORDER);
        List<String> names = getProductNames(dto);
        List<Product> products = productService.findAllByNameIn(names);
        Order order = toEntity(dto, products, ownerNumber, orderNumber);
        Set<String> productNumbers = stockItemService.reservation(StockItemMapper.toReservationDto(dto));
        setReservedStatus(order, productNumbers);
        order = repository.save(order);
        return toDto(order);
    }

    @Override
    @Transactional
    public OrderDto canceled(String number) {
        Order order = repository.findByNumber(number);
        OrderMapper.setCanceledStatus(order);
        order = repository.save(order);
        stockItemService.cancelReservation(StockItemMapper.toCancelReservationDto(order));
        return toDto(order);
    }

    @Override
    @Transactional
    public Optional<Order> findByNoTaskReserved() {
        return repository.findFirstByNoTaskReserved();
    }
}
