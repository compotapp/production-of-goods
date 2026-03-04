package com.pot.app.productionofgoods.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import static jakarta.persistence.FetchType.EAGER;

@Entity
@Table(name = "stock_items")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class StockItem extends BaseEntity{

    @OneToOne(fetch = EAGER)
    @JoinColumn(name = "product_number", referencedColumnName = "number", unique = true, nullable = false)
    private Product product;

    @Column(nullable = false)
    private Integer quantity;   // Фактическое наличие

    @Column(name = "min_stock", nullable = false)
    private Integer minStock;   // Минимальный остаток

    @Column(name = "max_stock", nullable = false)
    private Integer maxStock;   // Максимальный остаток
}
