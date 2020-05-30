package pl.uj.io.cuteanimals.service;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.uj.io.cuteanimals.model.Item;
import pl.uj.io.cuteanimals.repository.ItemsRepository;

@Service
public class ItemService {

    private static final Logger logger = LoggerFactory.getLogger(ItemService.class);
    private final ItemsRepository itemsRepository;

    @Autowired
    public ItemService(ItemsRepository itemsRepository) {
        this.itemsRepository = itemsRepository;
    }

    public List<Item> getAllItems() {
        return itemsRepository.findAll();
    }

    public Item getItem(int id) {
        Optional<Item> item = itemsRepository.findById(id);
        return item.orElseThrow(
                () ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND, "Unknown item with id " + id));
    }
}
