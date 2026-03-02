package com.pot.app.productionofgoods.scheduler;

import com.pot.app.productionofgoods.work.ItemWorkerService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private final ItemWorkerService itemWorkerService;

    public ScheduledTasks(ItemWorkerService itemWorkerService) {
        this.itemWorkerService = itemWorkerService;
    }

    @Scheduled(cron = "0 * * * * *")
    public void asd() {
        itemWorkerService.produce();
    }
}
