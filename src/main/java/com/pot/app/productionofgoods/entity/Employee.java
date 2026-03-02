package com.pot.app.productionofgoods.entity;


import com.pot.app.productionofgoods.enums.EmployeeStatus;
import com.pot.app.productionofgoods.enums.EmployeeTitle;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "employee")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)//Без параметра callSuper учитываются только поля текущего класса.
public class Employee extends BaseEntity {

    @Column(nullable = false)
    public String number;

    @Column(nullable = false)
    public int exp;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    public EmployeeTitle title;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    public EmployeeStatus status;

    @Version
    private int version;
}
