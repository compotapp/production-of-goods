package com.pot.app.productionofgoods.mapping;

import com.pot.app.productionofgoods.dto.TaskDto;
import com.pot.app.productionofgoods.entity.Task;

import static com.pot.app.productionofgoods.enums.TaskStatus.ACTIVE;
import static com.pot.app.productionofgoods.enums.TaskType.DELIVERY;

public class TaskMapper {

    public static Task toEntity(TaskDto dto, String number) {
        return Task.builder()
                .number(number)
                .productNumber(dto.productNumber())
                .orderNumber(dto.type() == DELIVERY ? dto.orderNumber() : null)
                .quantity(dto.quantity())
                .type(dto.type())
                .status(ACTIVE)
                .build();
    }

    public static TaskDto toDto(Task task) {
        return new TaskDto(
                task.getNumber(),
                task.getProductNumber(),
                task.getOrderNumber(),
                task.getQuantity(),
                task.getType(),
                task.getStatus()
        );
    }
}
