package com.pot.app.productionofgoods.entity;

import com.pot.app.productionofgoods.enums.ReservationStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "reservation")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)//Без параметра callSuper учитываются только поля текущего класса.
public class Reservation extends BaseEntity {

    @Column(nullable = false)
    private String number;  // Номер брони

    @Column(nullable = false)
    private String owner;  // Владелц брони

    @Column(nullable = false)
    private Integer quantity;  // Сколько товара забронировано

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReservationStatus status;  // ACTIVE, CONFIRMED, CANCELLED

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;  // ОДНОНАПРАВЛЕННАЯ связь - только бронь знает о товаре
}
