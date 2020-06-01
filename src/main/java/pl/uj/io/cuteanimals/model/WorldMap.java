package pl.uj.io.cuteanimals.model;

import java.util.HashMap;
import java.util.Map;
import pl.uj.io.cuteanimals.model.action.GoAction;
import pl.uj.io.cuteanimals.model.interfaces.ILocation;

public class WorldMap {
    private static final WorldMap instance = new WorldMap();
    private final Map<String, ILocation> locations = new HashMap<>();

    private WorldMap() {
        var town = new Town();
        var inn = new Inn();

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

    public static WorldMap getInstance() {
        return instance;
    }

    public ILocation getLocation(String name) {
        return locations.get(name);
    }
}
