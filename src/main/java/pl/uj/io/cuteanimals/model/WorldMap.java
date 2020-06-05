package pl.uj.io.cuteanimals.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import pl.uj.io.cuteanimals.model.action.*;
import pl.uj.io.cuteanimals.model.interfaces.IItem;
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
        var forest = new Forest();
        var forestGlade = new ForestGlade();
        var shamanCabin = new ShamanCabin();
        var dungeonEntrance = new DungeonEntrance();
        var dungeon = new Dungeon();
        var trap = new Trap();
        var chamberOfWealth = new ChamberOfWealth();
        var dungeonEgress = new DungeonEgress();
        var village = new Village();
        var inn = new Inn();
        var stable = new Stable();
        var devastatedLands = new DevastatedLands();
        var moat = new Moat();
        var castle = new Castle();
        var supervillain = new Supervillain();
        var medical = new Medical();

        // Common actions
        // These actions should be available from every location
        // TODO: refactor
        var showBackpack = new ShowBackpack();
        var showArmor = new ShowArmor();
        var throwAway = new ThrowAwayAction();
        var equipArmor = new EquipItem();
        var unequipArmor = new UnequipItem();
        var talkAction = new TalkAction(new ArrayList<>(), town);
        var showStats = new ShowStats();

        town.addAction("backpack", showBackpack);
        town.addAction("eq", showArmor);
        town.addAction("throw", throwAway);
        town.addAction("equip", equipArmor);
        town.addAction("off", unequipArmor);
        town.addAction("talk", talkAction);
        town.addAction("stats", showStats);
        forest.addAction("backpack", showBackpack);
        forest.addAction("eq", showArmor);
        forest.addAction("throw", throwAway);
        forest.addAction("equip", equipArmor);
        forest.addAction("off", unequipArmor);
        forest.addAction("stats", showStats);
        forestGlade.addAction("backpack", showBackpack);
        forestGlade.addAction("eq", showArmor);
        forestGlade.addAction("throw", throwAway);
        forestGlade.addAction("equip", equipArmor);
        forestGlade.addAction("off", unequipArmor);
        forestGlade.addAction("stats", showStats);
        shamanCabin.addAction("backpack", showBackpack);
        shamanCabin.addAction("eq", showArmor);
        shamanCabin.addAction("throw", throwAway);
        shamanCabin.addAction("equip", equipArmor);
        shamanCabin.addAction("off", unequipArmor);
        shamanCabin.addAction("talk", talkAction);
        shamanCabin.addAction("stats", showStats);
        medical.addAction("backpack", showBackpack);
        medical.addAction("eq", showArmor);
        medical.addAction("throw", throwAway);
        medical.addAction("equip", equipArmor);
        medical.addAction("off", unequipArmor);
        medical.addAction("stats", showStats);
        medical.addAction("talk", talkAction);
        dungeonEntrance.addAction("backpack", showBackpack);
        dungeonEntrance.addAction("eq", showArmor);
        dungeonEntrance.addAction("throw", throwAway);
        dungeonEntrance.addAction("equip", equipArmor);
        dungeonEntrance.addAction("off", unequipArmor);
        dungeonEntrance.addAction("stats", showStats);
        dungeon.addAction("backpack", showBackpack);
        dungeon.addAction("eq", showArmor);
        dungeon.addAction("throw", throwAway);
        dungeon.addAction("equip", equipArmor);
        dungeon.addAction("off", unequipArmor);
        dungeon.addAction("stats", showStats);
        trap.addAction("backpack", showBackpack);
        trap.addAction("eq", showArmor);
        trap.addAction("throw", throwAway);
        trap.addAction("equip", equipArmor);
        trap.addAction("off", unequipArmor);
        trap.addAction("stats", showStats);
        chamberOfWealth.addAction("backpack", showBackpack);
        chamberOfWealth.addAction("eq", showArmor);
        chamberOfWealth.addAction("throw", throwAway);
        chamberOfWealth.addAction("equip", equipArmor);
        chamberOfWealth.addAction("off", unequipArmor);
        chamberOfWealth.addAction("stats", showStats);
        dungeonEgress.addAction("backpack", showBackpack);
        dungeonEgress.addAction("eq", showArmor);
        dungeonEgress.addAction("throw", throwAway);
        dungeonEgress.addAction("equip", equipArmor);
        dungeonEgress.addAction("off", unequipArmor);
        dungeonEgress.addAction("stats", showStats);
        village.addAction("backpack", showBackpack);
        village.addAction("eq", showArmor);
        village.addAction("throw", throwAway);
        village.addAction("equip", equipArmor);
        village.addAction("off", unequipArmor);
        village.addAction("stats", showStats);
        inn.addAction("backpack", showBackpack);
        inn.addAction("eq", showArmor);
        inn.addAction("throw", throwAway);
        inn.addAction("equip", equipArmor);
        inn.addAction("off", unequipArmor);
        inn.addAction("talk", talkAction);
        inn.addAction("stats", showStats);
        stable.addAction("backpack", showBackpack);
        stable.addAction("eq", showArmor);
        stable.addAction("throw", throwAway);
        stable.addAction("equip", equipArmor);
        stable.addAction("off", unequipArmor);
        stable.addAction("talk", talkAction);
        stable.addAction("stats", showStats);
        devastatedLands.addAction("backpack", showBackpack);
        devastatedLands.addAction("eq", showArmor);
        devastatedLands.addAction("throw", throwAway);
        devastatedLands.addAction("equip", equipArmor);
        devastatedLands.addAction("off", unequipArmor);
        devastatedLands.addAction("stats", showStats);
        moat.addAction("backpack", showBackpack);
        moat.addAction("eq", showArmor);
        moat.addAction("throw", throwAway);
        moat.addAction("equip", equipArmor);
        moat.addAction("off", unequipArmor);
        moat.addAction("talk", talkAction);
        moat.addAction("stats", showStats);
        castle.addAction("backpack", showBackpack);
        castle.addAction("eq", showArmor);
        castle.addAction("throw", throwAway);
        castle.addAction("equip", equipArmor);
        castle.addAction("off", unequipArmor);
        castle.addAction("stats", showStats);
        supervillain.addAction("backpack", showBackpack);
        supervillain.addAction("eq", showArmor);
        supervillain.addAction("throw", throwAway);
        supervillain.addAction("equip", equipArmor);
        supervillain.addAction("off", unequipArmor);
        supervillain.addAction("talk", talkAction);
        supervillain.addAction("stats", showStats);

        // Init town
        // TODO: move this to location builder or something
        town.addAction(
                "investigate",
                new InvestigateAction(
                        "Looking around all you can see are empty streets and"
                                + "desolated buildings. The only person in your eyesight "
                                + "is your headman and he looks like he wants to talk to you..."));

        forest.addAction(
                "investigate",
                new InvestigateAction(
                        "You are surrounded by oaks and ferns."
                                + "Wild animals seem to be restless."
                                + "Suddenly little squirrel jumps on your shoulder!"
                                + "She has something important to tell you!"));

        forestGlade.addAction(
                "investigate",
                new InvestigateAction(
                        "In the middle of glade you spot something shinny."
                                + "You are drawn to this object by the power of magic..."));

        shamanCabin.addAction(
                "investigate", new InvestigateAction("You are blinded! You can't see anything!"));

        medical.addAction(
                "investigate",
                new InvestigateAction(
                        "You are in the cabin... all you can see is various mixtures and medical tools."));

        dungeonEntrance.addAction(
                "investigate",
                new InvestigateAction(
                        "What are you looking at?" + "The bridge won't cross itself!"));

        dungeon.addAction(
                "investigate",
                new DungeonInvestigateAction(
                        "Your torch allows you to see drawings and symbols on the walls,"
                                + "pillaged and wrecked by time itself... Further ahead are two paths."));

        trap.addAction("investigate", new InvestigateAction("You feel too weak to look around..."));

        chamberOfWealth.addAction(
                "investigate",
                new InvestigateAction(
                        "You notice that treasure had to be stuck here for hundreds years..."));

        dungeonEgress.addAction(
                "investigate",
                new InvestigateAction(
                        "You can't see any useful objects around you "
                                + "you have to deal with them with what you got!"));

        village.addAction(
                "investigate",
                new InvestigateAction(
                        "In front of you devastated lands stretching out for several "
                                + "hundred kilometers across but you also spot the inn and the stable."));

        inn.addAction(
                "investigate",
                new InvestigateAction(
                        "The innkeeper is a short female. Room is full of injured soldiers... "
                                + "You can hear someone is chanting songs of the fallen lands. "
                                + "While you are looking around you spot a stranger "
                                + "who approach you. He seems very drunk and out of nowhere "
                                + "disgrace your name... (You cant talk or fight)"));

        stable.addAction(
                "investigate",
                new InvestigateAction(
                        "You spot a tall man who smiles at you and "
                                + "looks like he wants to offer something."));

        devastatedLands.addAction(
                "investigate",
                new InvestigateAction("There is no time to investigate! Hurry or you will die!"));

        moat.addAction(
                "investigate",
                new InvestigateAction(
                        "You are looking around and search something that could help you "
                                + "pass the moat... you can't see anything useful but thin piece of wood. "
                                + "You pick it up and place it across the moat. Now you can use it to go 'through'!"));

        castle.addAction(
                "investigate",
                new InvestigateAction(
                        "A massive granite door blocked your path. Dire warning messages are all over it, "
                                + "somehow untouched by time. You step closer to inspect it and... wait... you think "
                                + "you can hear a whisper coming from behind the door... You can feel that "
                                + "evil power is closer then ever... waiting on you."));

        supervillain.addAction(
                "investigate",
                new InvestigateAction(
                        "You looked around but the only thing you could see were visions of all those "
                                + "horrible things that must have happened in here..."));

        // TODO: add all items to pick up
        var townItems = new HashMap<String, IItem>();
        townItems.put("shield", itemService.getItem(2));
        townItems.put("tank", itemService.getItem(3));
        town.addAction("pick", new PickupAction(townItems));

        var chadBackpack = new Backpack();
        chadBackpack.putItem(itemService.getItem(1));
        var chad =
                new NPC(
                        null,
                        chadBackpack,
                        "Chad",
                        List.of("Riichi", "Tsumo", "Iipeko", "Dora dora dora"));

        // TODO: add all NPCs
        town.addNPC(chad);

        // Make connections
        town.addAction(
                "go",
                new GoAction(
                        Map.of(
                                "forest", forest,
                                "woods", forest)));

        forest.addAction(
                "go",
                new GoAction(
                        Map.of(
                                "left", forestGlade,
                                "right", shamanCabin)));

        forestGlade.addAction("go", new GoAction(Map.of("straight", dungeonEntrance)));

        shamanCabin.addAction("go", new GoAction(Map.of("inside", dungeonEntrance)));

        medical.addAction("go", new GoAction(Map.of("dungeon", dungeonEntrance)));

        dungeonEntrance.addAction("go", new DungeonEntranceGoAction(Map.of("dungeon", dungeon)));

        dungeon.addAction(
                "go",
                new GoAction(
                        Map.of(
                                "left", trap,
                                "right", chamberOfWealth)));

        trap.addAction("go", new GoAction(Map.of("straight", dungeonEgress)));

        chamberOfWealth.addAction("go", new GoAction(Map.of("straight", dungeonEgress)));

        dungeonEgress.addAction("go", new GoAction(Map.of("straight", village)));

        village.addAction(
                "go",
                new GoAction(
                        Map.of(
                                "stable", stable,
                                "inn", inn,
                                "tavern", inn,
                                "devastated lands", devastatedLands)));

        inn.addAction("go", new GoAction(Map.of("village", village)));

        stable.addAction("go", new GoAction(Map.of("straight", moat)));

        devastatedLands.addAction("go", new GoAction(Map.of("straight", moat)));

        moat.addAction("go", new GoAction(Map.of("through", castle)));

        castle.addAction("go", new GoAction(Map.of("straight", supervillain)));

        // Add to map
        locations.put("town", town);
        locations.put("forest", forest);
        locations.put("forest glade", forestGlade);
        locations.put("shaman's cabin", shamanCabin);
        locations.put("medical", medical);
        locations.put("dungeon entrance", dungeonEntrance);
        locations.put("dungeon", dungeon);
        locations.put("trap", trap);
        locations.put("chamber of wealth", chamberOfWealth);
        locations.put("dungeon egress", dungeonEgress);
        locations.put("village", village);
        locations.put("inn", inn);
        locations.put("stable", stable);
        locations.put("devastated lands", devastatedLands);
        locations.put("moat", moat);
        locations.put("castle", castle);
        locations.put("supervillain", supervillain);
    }

    public ILocation getLocation(String name) {
        return locations.get(name);
    }
}
