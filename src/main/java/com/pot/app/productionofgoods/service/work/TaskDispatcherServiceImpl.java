package com.pot.app.productionofgoods.service.work;

import com.pot.app.productionofgoods.entity.Task;
import com.pot.app.productionofgoods.service.NumberGeneratorService;
import com.pot.app.productionofgoods.service.OrderService;
import com.pot.app.productionofgoods.service.StockItemService;
import com.pot.app.productionofgoods.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.pot.app.productionofgoods.entity.NumberSequence.SequenceType.TASK;
import static com.pot.app.productionofgoods.enums.TaskStatus.WORK;
import static com.pot.app.productionofgoods.mapping.work.DirectoryMapper.createTask;

@Service
@Slf4j
@RequiredArgsConstructor
public class TaskDispatcherServiceImpl implements TaskDispatcherService {

    private final NumberGeneratorService numberGenerator;
    private final StockItemService stockItemService;
    private final TaskService taskService;
    private final OrderService orderService;

    private static void accept(Task task) {
        log.debug("создана задача номер: {}, тип: {}", task.getNumber(), task.getType().getTitle());
    }

    @Override
    @Transactional
    public Optional<Task> createTaskMinStock() {
        return stockItemService.findByNoTaskMinStock()
                .map(stockItem -> {
                    Task task = taskService.save(createTask(stockItem, numberGenerator.generateNumber(TASK)));
                    accept(task);
                    return task;
                });
    }

    @Override
    @Transactional
    public List<Task> createTaskProduction() {
        return orderService.findByNoTaskReserved()
                .map(order -> {
                    List<String> numbers = numberGenerator.generateNumbers(TASK, order.getItems().size());
                    List<Task> tasks = taskService.saveAll(createTask(order, numbers));
                    tasks.forEach(TaskDispatcherServiceImpl::accept);
                    return tasks;
                }).orElseGet(ArrayList::new);
    }

    @Override
    @Transactional
    public Optional<Task> takeOnTask() {
        return taskService.findByStatusActive()
                .map(task -> {
                    task.setStatus(WORK);
                    task = taskService.save(task);
                    log.debug("задача в работе номер:{}, тип: {}", task.getNumber(), task.getType().getTitle());
                    return task;
                });
    }
}
