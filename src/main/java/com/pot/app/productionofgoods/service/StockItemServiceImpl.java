package com.pot.app.productionofgoods.service;

import com.pot.app.productionofgoods.dto.StockItemDto;
import com.pot.app.productionofgoods.entity.Product;
import com.pot.app.productionofgoods.entity.StockItem;
import com.pot.app.productionofgoods.repository.jpa.StockItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.pot.app.productionofgoods.mapping.StockItemMapper.*;

@Service
@RequiredArgsConstructor
public class StockItemServiceImpl implements StockItemService {

    private final StockItemRepository repository;
    private final ProductService productService;

    @Override
    @Transactional
    public StockItemDto save(StockItemDto dto) {
        Product product = productService.findByName(dto.productName());
        StockItem stockItem = repository.save(toEntity(dto, product));
        return toDto(stockItem);
    }

    @Override
    @Transactional
    public List<StockItemDto> save(List<StockItemDto> dtos) {
        List<String> names = getNames(dtos);
        List<Product> products = productService.findAllByNameIn(names);
        List<StockItem> stockItems = repository.saveAll(toEntity(dtos, products));
        return toDto(stockItems);
    }

    @Override
    @Transactional
    public Set<String> cancelReservation(List<StockItemDto> dtos) {
        DataForUpdate dataForUpdate = getDataForUpdate(dtos, true);
        return repository.updateStockItems(dataForUpdate.numbers(), dataForUpdate.quantities());
    }

    @Override
    @Transactional
    public Set<String> reservation(List<StockItemDto> dtos) {
        DataForUpdate dataForUpdate = getDataForUpdate(dtos, false);
        return repository.updateStockItems(dataForUpdate.numbers(), dataForUpdate.quantities());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<StockItem> findByNoTaskMinStock() {
        return repository.findFirstByNoTaskMinStock();
    }

    @Override
    @Transactional
    public void addQuantity(String productNumber, int quantity) {
        repository.addQuantity(productNumber, quantity);
    }
}
