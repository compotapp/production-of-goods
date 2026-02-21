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

    @Column(name = "amount", nullable = false)
    Integer amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false)
    GoodsCategory category;
}