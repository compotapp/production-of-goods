package com.pot.app.productionofgoods.entity;

import com.pot.app.productionofgoods.enums.TaskStatus;
import com.pot.app.productionofgoods.enums.TaskType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import static jakarta.persistence.EnumType.STRING;

@Entity
@Table(name = "tasks")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Task extends BaseEntity {

    @Column(unique = true, nullable = false)
    String number;

    @Column(nullable = false)
    String productNumber;

    @Column(nullable = true)
    String orderNumber;

    @Column(nullable = false)
    int quantity;

    @Column(nullable = false)
    @Builder.Default
    int completeQuantity = 0;

    @Enumerated(STRING)
    @Column(nullable = false)
    TaskType type;

    @Enumerated(STRING)
    @Column(nullable = false)
    TaskStatus status;
}
