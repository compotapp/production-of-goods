package com.pot.app.productionofgoods.service;

import com.pot.app.productionofgoods.dto.EmployeeDto;
import com.pot.app.productionofgoods.entity.Employee;
import com.pot.app.productionofgoods.repository.jpa.EmployeeRepository;
import org.springframework.stereotype.Service;

import static com.pot.app.productionofgoods.mapping.EmployeeMapping.toEntity;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeServiceImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

    public Employee create(EmployeeDto dto) {
        return repository.save(toEntity(dto));
    }
}
