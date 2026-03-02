package com.pot.app.productionofgoods.repository.jpa;

import com.pot.app.productionofgoods.entity.Employee;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends GeneralRepository<Employee, Long>{
}
