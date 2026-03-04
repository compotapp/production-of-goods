package com.pot.app.productionofgoods.service;

import com.pot.app.productionofgoods.dto.ProductDto;
import com.pot.app.productionofgoods.entity.Product;
import com.pot.app.productionofgoods.repository.jpa.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.pot.app.productionofgoods.entity.NumberSequence.SequenceType.PRODUCT;
import static com.pot.app.productionofgoods.mapping.ProductMapper.toDto;
import static com.pot.app.productionofgoods.mapping.ProductMapper.toEntity;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private final NumberGeneratorService numberGenerator;

    @Override
    @Transactional
    public ProductDto create(ProductDto dto) {
        String number = numberGenerator.generateNumber(PRODUCT);
        Product product = repository.save(toEntity(dto, number));
        return toDto(product);
    }

    @Override
    @Transactional
    public List<ProductDto> create(List<ProductDto> dtos) {
        List<String> numbers = numberGenerator.generateNumbers(PRODUCT, dtos.size());
        List<Product> products = repository.saveAll(toEntity(dtos, numbers));
        return toDto(products);
    }

    @Override
    @Transactional(readOnly = true)
    public Product findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAllByNameIn(List<String> names) {
        return repository.findAllByNameIn(names);
    }
}
