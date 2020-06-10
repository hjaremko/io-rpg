package pl.uj.io.cuteanimals.service;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.uj.io.cuteanimals.model.ItemClass;
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
                        "Sword",
                        "Regular sword",
                        3,
                        attributesService.getAttributes(1),
                        ItemType.WEAPON,
                        ItemClass.WARRIOR));
        addItem(
                new Item(
                        2,
                        "Shield",
                        "Regular shield",
                        3,
                        attributesService.getAttributes(2),
                        ItemType.ARMOR,
                        ItemClass.WARRIOR));
        addItem(
                new Item(
                        3,
                        "Magic wand",
                        "Basic magic wand",
                        1,
                        attributesService.getAttributes(3),
                        ItemType.WEAPON,
                        ItemClass.MONK));

        addItem(
                new Item(
                        4,
                        "Bow",
                        "Regular bow",
                        2,
                        attributesService.getAttributes(4),
                        ItemType.WEAPON,
                        ItemClass.ARCHER));

        addItem(
                new Item(
                        5,
                        "Arrow",
                        "regular arrow",
                        1,
                        attributesService.getAttributes(5),
                        ItemType.WEAPON,
                        ItemClass.ARCHER));

        addItem(
                new Item(
                        6,
                        "Coin",
                        "Golden coin",
                        1,
                        attributesService.getAttributes(6),
                        ItemType.USABLE,
                        ItemClass.ANY));

        addItem(
                new Item(
                        7,
                        "Amulet",
                        "Amulet of eternal love",
                        1,
                        attributesService.getAttributes(7),
                        ItemType.NEUTRAL,
                        ItemClass.ANY));

        addItem(
                new Item(
                        8,
                        "Torch",
                        "Regular torch",
                        3,
                        attributesService.getAttributes(8),
                        ItemType.WEAPON,
                        ItemClass.ANY));
        addItem(
                new Item(
                        9,
                        "Apple",
                        "Moist nad delicious.",
                        1,
                        attributesService.getAttributes(9),
                        ItemType.USABLE,
                        ItemClass.ANY));
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
