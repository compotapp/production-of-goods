package com.pot.app.productionofgoods.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.EnumType.STRING;

@Entity
@Table(name = "number_sequences")
@Data
@NoArgsConstructor
public class NumberSequence {

    @Id
    @Enumerated(STRING)
    private SequenceType type;  // PRODUCT или DELIVERY_REQUEST

    @Column(name = "last_number", nullable = false)
    private Integer lastNumber;  // 0, 1, 2, 3...

    @Version  // защита от одновременного изменения двумя пользователями
    private Long version;

    public NumberSequence(SequenceType type) {
        this.type = type;
        this.lastNumber = 0;
    }

    // Метод получает следующий номер и увеличивает счетчик
    public Integer getNextNumber() {
        return lastNumber++;
    }

    public List<Integer> getNextNumbers(int count) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            numbers.add(lastNumber + i);
        }
        lastNumber = lastNumber + count;
        return numbers;
    }

    @Getter
    @AllArgsConstructor
    public enum SequenceType {
        ORDER("ORD"),
        OWNER("OWN"),
        PRODUCT("PRD");

        private final String prefix;
    }
}