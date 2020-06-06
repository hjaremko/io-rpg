package pl.uj.io.cuteanimals.service;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.uj.io.cuteanimals.model.ItemType;
import pl.uj.io.cuteanimals.model.entity.Item;
import pl.uj.io.cuteanimals.repository.ItemsRepository;

@Service
public class ItemService {

    private static final Logger logger = LoggerFactory.getLogger(ItemService.class);
    private final ItemsRepository itemsRepository;

    @Autowired
    public ItemService(ItemsRepository itemsRepository) {
        this.itemsRepository = itemsRepository;
    }

    @Autowired
    public void initialize(AttributesService attributesService) {
        // Add dummy items to prevent game from crashing
        // TODO: remove
        logger.debug("Inserting dummy items");
        addItem(
                new Item(
                        1,
                        "Miecz",
                        "Zwykły miecz",
                        3,
                        attributesService.getAttributes(1),
                        ItemType.WEAPON));
        addItem(
                new Item(
                        2,
                        "Tarcza",
                        "Zwykła tarcza",
                        3,
                        attributesService.getAttributes(2),
                        ItemType.ARMOR));
        addItem(
                new Item(
                        3,
                        "durzy czołg",
                        "boje sie go",
                        20,
                        attributesService.getAttributes(3),
                        ItemType.WEAPON));
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

    public void addItem(Item item) {
        itemsRepository.saveAndFlush(item);
    }
}
