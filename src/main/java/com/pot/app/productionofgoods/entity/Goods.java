package com.pot.app.productionofgoods.entity;

import com.pot.app.productionofgoods.enums.GoodsCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "goods")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)//Без параметра callSuper учитываются только поля текущего класса.
public class Goods extends BaseEntity {

    @Column(name = "name", unique = true, nullable = false)
    String name;

    @Column(name = "quantity", nullable = false)
    Integer quantity;

    @Column(name = "category", nullable = false)
    @Enumerated(EnumType.STRING)
    GoodsCategory category;

    public void plusQuantity(Integer quantity) {
        this.quantity = this.quantity + quantity;
    }

    public void minusQuantity(Integer quantity) {
        this.quantity = this.quantity + quantity;
    }
}