package com.pot.app.productionofgoods.mapping;

import com.pot.app.productionofgoods.dto.EmployeeDto;
import com.pot.app.productionofgoods.entity.Employee;

import static com.pot.app.productionofgoods.enums.EmployeeStatus.FREE;
import static com.pot.app.productionofgoods.enums.EmployeeTitle.valueOf;

public class EmployeeMapping {

    public static Employee toEntity(EmployeeDto dto) {
        return Employee.builder()
                .number(dto.number())
                .exp(dto.exp())
                .title(valueOf(dto.status().toUpperCase()))
                .status(FREE)
                .build();
    }
}
