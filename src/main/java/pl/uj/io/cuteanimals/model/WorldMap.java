package pl.uj.io.cuteanimals.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import pl.uj.io.cuteanimals.model.action.GoAction;
import pl.uj.io.cuteanimals.model.action.InvestigateAction;
import pl.uj.io.cuteanimals.model.action.PickupAction;
import pl.uj.io.cuteanimals.model.action.TalkAction;
import pl.uj.io.cuteanimals.model.interfaces.ILocation;
import pl.uj.io.cuteanimals.service.ItemService;

public class WorldMap {
    private static final WorldMap instance = new WorldMap();
    private final Map<String, ILocation> locations = new HashMap<>();

    private WorldMap() {}

    public static WorldMap getInstance() {
        return instance;
    }

    public void initialize(ItemService itemService) {
        var town = new Town();
        var inn = new Inn();

        // Init town
        // TODO: move this to location builder or something
        town.addAction(
                "investigate",
                new InvestigateAction(
                        "Looking around you catch a glimpse of small inn. "
                                + "You also notice a shield on the street. "));

        // Example item to pick up
        town.addAction("pick", new PickupAction(itemService.getItem(2), List.of("shield")));

        // Common actions
        // These actions should be available from every location
        // TODO: refactor
        var showBackpack = new ShowBackpack();
        var showArmor = new ShowArmor();
        var throwAway = new ThrowAwayAction();
        var equipArmor = new EquipItem();
        var unequipArmor = new UnequipItem();
        var talkAction = new TalkAction(new ArrayList<>(), town);
        town.addAction("backpack", showBackpack);
        town.addAction("eq", showArmor);
        town.addAction("throw", throwAway);
        town.addAction("equip", equipArmor);
        town.addAction("off", unequipArmor);
        town.addAction("talk", talkAction);
        inn.addAction("backpack", showBackpack);
        inn.addAction("eq", showArmor);
        inn.addAction("throw", throwAway);
        inn.addAction("equip", equipArmor);
        inn.addAction("off", unequipArmor);
        // Make connections
        town.addAction(
                "go",
                new GoAction(
                        Map.of(
                                "inn", inn,
                                "tavern", inn)));
        inn.addAction(
                "go",
                new GoAction(
                        Map.of(
                                "town", town,
                                "karka han", town)));

        // Add to map
        locations.put("town", town);
        locations.put("inn", inn);
    }

    public ILocation getLocation(String name) {
        return locations.get(name);
    }
}
