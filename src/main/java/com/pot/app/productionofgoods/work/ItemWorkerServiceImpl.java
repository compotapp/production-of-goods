package com.pot.app.productionofgoods.work;

import com.pot.app.productionofgoods.entity.Item;
import com.pot.app.productionofgoods.enums.ItemName;
import com.pot.app.productionofgoods.factory.ItemFactory;
import com.pot.app.productionofgoods.service.ItemService;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.pot.app.productionofgoods.enums.ItemCategory.FRUIT;

@Service
public class ItemWorkerServiceImpl implements ItemWorkerService {

    private final ItemFactory factory;
    private final ItemService service;

    public ItemWorkerServiceImpl(ItemFactory factory, ItemService service) {
        this.factory = factory;
        this.service = service;
    }

    @Override
    public void produce() {
        List<ItemName> names = ItemName.findAllByCategory(FRUIT);
        List<Item> items = names.stream()
                .map(factory::create)
                .toList();
        service.updateAllByName(items);
    }
}
