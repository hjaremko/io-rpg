package pl.uj.io.cuteanimals.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.uj.io.cuteanimals.model.Item;
import pl.uj.io.cuteanimals.repository.ItemsRepository;

@Service
public class ItemService {
    private final ItemsRepository itemsRepository;

    @Autowired
    public ItemService(ItemsRepository itemsRepository) {
        this.itemsRepository = itemsRepository;
    }

    public List<Item> getAllItems() {
        return itemsRepository.findAll();
    }
}
