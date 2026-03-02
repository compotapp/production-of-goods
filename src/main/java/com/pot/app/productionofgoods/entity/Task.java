package com.pot.app.productionofgoods.entity;

import com.pot.app.productionofgoods.enums.ItemName;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import static jakarta.persistence.EnumType.STRING;

@Entity
@Table(name = "task")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)//Без параметра callSuper учитываются только поля текущего класса.
public class Task extends BaseEntity{

    @Enumerated(STRING)
    @Column(name = "item_name", nullable = false)
    public ItemName itemName;

    @Column(name = "quantity", nullable = false)
    public int quantity;

    @Column(name = "is_active", nullable = false)
    public boolean isActive;
}
