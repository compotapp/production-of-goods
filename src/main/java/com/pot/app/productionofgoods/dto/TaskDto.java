package com.pot.app.productionofgoods.dto;

import com.pot.app.productionofgoods.enums.TaskStatus;
import com.pot.app.productionofgoods.enums.TaskType;

public record TaskDto(
        String number,
        String productNumber,
        String orderNumber,
        Integer quantity,
        TaskType type,
        TaskStatus status
) {
}
