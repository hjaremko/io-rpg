package pl.uj.io.cuteanimals.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import pl.uj.io.cuteanimals.action.GoAction;
import pl.uj.io.cuteanimals.action.InvestigateAction;
import pl.uj.io.cuteanimals.action.PickupAction;
import pl.uj.io.cuteanimals.action.TalkAction;
import pl.uj.io.cuteanimals.location.LocationBuilder;
import pl.uj.io.cuteanimals.model.interfaces.IItem;
import pl.uj.io.cuteanimals.model.interfaces.ILocation;
import pl.uj.io.cuteanimals.plot.actions.DungeonEntranceGoAction;
import pl.uj.io.cuteanimals.plot.actions.DungeonInvestigateAction;
import pl.uj.io.cuteanimals.plot.actions.TrapEntranceRemoveHealthAction;
import pl.uj.io.cuteanimals.service.ItemService;

public final class WorldMap {
    private static final WorldMap instance = new WorldMap();
    private final Map<String, ILocation> locations = new HashMap<>();

    private WorldMap() {}

    public static WorldMap getInstance() {
        return instance;
    }

    public void initialize(ItemService itemService) {
        var town = new DefaultLocation();
        var forest = new DefaultLocation();
        var forestGlade = new DefaultLocation();
        var shamanCabin = new DefaultLocation();
        var dungeonEntrance = new DefaultLocation();
        var dungeon = new DefaultLocation();
        var trap = new DefaultLocation();
        var chamberOfWealth = new DefaultLocation();
        var dungeonEgress = new DefaultLocation();
        var village = new DefaultLocation();
        var inn = new DefaultLocation();
        var stable = new DefaultLocation();
        var devastatedLands = new DefaultLocation();
        var moat = new DefaultLocation();
        var castle = new DefaultLocation();
        var supervillain = new DefaultLocation();
        var medicalCabin = new DefaultLocation();

        // All NPCs
        var headmanBackpack = new Backpack();
        headmanBackpack.putItem(itemService.getItem(1));
        var headman =
                new NPC(
                        null,
                        headmanBackpack,
                        "Headman",
                        List.of(
                                "Your clan needs your help. Dark powers come from the most monstrous creature walking on Earth. "
                                        + "Master of evil Fasilius has already conquered the other three animal lines, the only survivor is yours...",
                                "Now, enemy forces have turned to your lands. You are the last living being who can stop him! I have chosen you to save us."
                                        + "In order to do that you have to defeat Fasilius!",
                                "I brought you shield, sword, magic wand, bow, arrows and some money. "
                                        + "Take what you need and head out to save our future."));

        var JingleBackpack = new Backpack();
        headmanBackpack.putItem(itemService.getItem(1));
        var magicSquirrel =
                new NPC(
                        null,
                        JingleBackpack,
                        "Jingle",
                        List.of(
                                "Hello, traveler. I am magic squirrel Jingle. My father, the greatest of the squirrel mages, saved me from being killed by Fasilius. "
                                        + "Before his death he told me to warn the remaining clans... unfortunately only yours survived. "
                                        + "I bring a story about the tragic occurrences that happened to our lands...",
                                "In my dreams I still see the dead bodies of animals that fought for their lives with all their strength. "
                                        + "The atrocities with which enemy forces have plundered villages cannot be described. "
                                        + "They killed children and women, throwing them to be eaten by beings who barely resembled animals. "
                                        + "Yellow-purple foam flowed from their mouths, and there was nothing in their eyes but pure madness. "
                                        + "Men were handcuffed. Shackles wound their wrists and ankles to their bones, "
                                        + "and later dragged them dying as prisoners straight into Fasilius' Castle.",
                                "I can't talk about it anymore, good luck!"));

        var shaman =
                new NPC(
                        null,
                        null,
                        "Thaddeus",
                        List.of(
                                "I didn't expect guests...",
                                "Either way, I'm Thaddeus, make yourself comfortable, I haven't talked to anyone in a long time. "
                                        + "I will prepare an infusion of earthy leaves. You've probably heard of Fasylius' conquests...",
                                "Fasilius chose a particularly cruel way to conquer the world. He hired a group of magicians who created "
                                        + "a magic potion that turned every animal into a warrior entirely devoted to the forces of evil. "
                                        + "If the mixture doesn't work, the unfortunate who was given it changes into a brainless being who only wants to murder.",
                                "That's all I know about it. I'm going back to work. Stay as long as you like and good luck on your journey wherever you go. "
                                        + "It was nice to talk to someone, soon from this loneliness I will start talking to stones."));

        var medical =
                new NPC(
                        null,
                        null,
                        "Britt",
                        List.of(
                                "You finally woke up...",
                                "You probably wonder where you are, don't worry, I'm not your enemy. They call me Britt and I'm a medical. "
                                        + "Last night a magical squirrel named Jingle informed me and my husband about an accident that occurred on the bridge... "
                                        + "I guess you tried to get to the other side. I cured you and you can go back to the dungeon. I am returning to healing the wounded."));

        var beast1 =
                new NPC(
                        null,
                        null,
                        "Hubhert",
                        List.of("Wooooaaahhhhhh!!! Smash! Smash! Smaaaaash!"));

        var beast2 = new NPC(null, null, "Marc", List.of("Wrrr..."));

        var innNPC1 =
                new NPC(
                        null,
                        null,
                        "Domenic",
                        List.of(
                                "I have barely survived, I am so exhausted...",
                                "Zzz...",
                                "...zzz...",
                                "...zzz...",
                                "...zzz..."));

        var innNPC2 =
                new NPC(
                        null,
                        null,
                        "Raphael",
                        List.of(
                                "I lost all my relatives, my city was razed to the ground...",
                                "*sobbing*"));

        var innNPC3 =
                new NPC(
                        null,
                        null,
                        "Avery",
                        List.of(
                                "I help to heal the wounded, but they are still coming...",
                                "We are running out of antidote..."));

        var innNPC4 =
                new NPC(
                        null,
                        null,
                        "Rory",
                        List.of(
                                "I miss him so much! He was my whole world, we had plans... now he's dead!",
                                "If I could, I would kill Fasilius with my bare hands for what he has done"));

        var innNPC5 =
                new NPC(
                        null,
                        null,
                        "Galen",
                        List.of(
                                "*chanting* Distant lands obsessed with ash and destruction, beautiful ladies cry for their lovers...",
                                "*still chanting* Ohhhh they cry, ohhhh they cry, they cry",
                                "*and still chanting*"));

        var innkeeper = new NPC(null, null, "Hilda", List.of("I'm too busy to talk to you."));

        var stranger =
                new NPC(
                        null,
                        null,
                        "Darell",
                        List.of(
                                "What do you want you dirty... stinky... motherfucker?!",
                                "Do you want to fight? I will break your bones... "
                                        + "(Fight him or leave him alone because he is too drunk to talk)"));

        var horseBoy =
                new NPC(
                        null,
                        null,
                        "Elvin",
                        List.of(
                                "Hello traveler, you look tired. In my stable there are the best horses that survived. "
                                        + "I heard that you set out on this dangerous journey to save us. In return, I would like to give you my best steed, Bernard...",
                                "Take good care of him, and good luck!"));

        var warrior1 = new NPC(null, null, "Lucas", List.of("Taste my sword you fagot!"));

        var warrior2 =
                new NPC(
                        null,
                        null,
                        "Claudius",
                        List.of("If I were you I would run as fast as I can..."));

        var warrior3 = new NPC(null, null, "Julian", List.of("I will cut off all your limbs!"));

        var warrior4 = new NPC(null, null, "Annis", List.of("I will break your neck!"));

        var mag1 = new NPC(null, null, "Herschel", List.of("Please... help us!"));

        var mag2 =
                new NPC(
                        null,
                        null,
                        "Merlin",
                        List.of("Fasilius is using us as his weapon...", "Let us free!"));

        var mag3 = new NPC(null, null, "Donovan", List.of("Oh I'm begging you..."));

        var fasilius =
                new NPC(
                        null,
                        null,
                        "Fasilius",
                        List.of(
                                "I was expecting you... I see you noticed your predecessor. As you can see, it didn't end well for him... "
                                        + "killing him was a minor matter. Do I see tears in your eyes? Don't worry, his life was worth nothing anyway. "
                                        + "I hope that the fight with you will be more exciting ...",
                                "Your nation is the last obstacle to me taking over the whole world. Every little creature on this earth will have to submit to my power. "
                                        + "A new era begins ... An era in which I proclaim the truth.",
                                "Come on and fight me if you dare!"));

        var townItems = new HashMap<String, IItem>();
        townItems.put("sword", itemService.getItem(1));
        townItems.put("shield", itemService.getItem(2));
        townItems.put("wand", itemService.getItem(3));
        townItems.put("bow", itemService.getItem(4));
        townItems.put("arrow", itemService.getItem(5));
        townItems.put("coin", itemService.getItem(6));
        town =
                new LocationBuilder(town)
                        .addDefaultActions()
                        .setDescription(
                                "You are in your hometown. Every place is currently closed even an elegant inn, built within"
                                        + "an ancient tower of rune-carved stone which usually bustling with life."
                                        + "You can feel the terror in the air. Your leader has a task for you, approach him to talk to him.")
                        .addAction("talk", new TalkAction(town))
                        .addAction(
                                "investigate",
                                new InvestigateAction(
                                        "Looking around all you can see are empty streets and"
                                                + "desolated buildings. The only person in your eyesight "
                                                + "is your headman and he looks like he wants to talk to you..."
                                                + "(Creatures around you: Headman)"))
                        .addAction("go", new GoAction(Map.of("straight", forest)))
                        .addAction("pick", new PickupAction(townItems))
                        .addNPC(headman)
                        .build();

        forest =
                new LocationBuilder(forest)
                        .addDefaultActions()
                        .setDescription(
                                "You enter the forest. You can fell warm sunlight coming"
                                        + "through the green treetops. The sounds of nature surround you."
                                        + "In the distance you spot fork in the road."
                                        + "(You can go left or right).")
                        .addAction("talk", new TalkAction(forest))
                        .addAction(
                                "investigate",
                                new InvestigateAction(
                                        "You are surrounded by oaks and ferns."
                                                + "Wild animals seem to be restless."
                                                + "Suddenly little squirrel jumps on your shoulder!"
                                                + "She has something important to tell you!"
                                                + "(Creatures around you: Jingle)"))
                        .addAction(
                                "go",
                                new GoAction(
                                        Map.of(
                                                "left", forestGlade,
                                                "right", shamanCabin)))
                        .addNPC(magicSquirrel)
                        .build();

        var gladeItems = new HashMap<String, IItem>();
        gladeItems.put("amulet", itemService.getItem(7));
        forestGlade =
                new LocationBuilder(forestGlade)
                        .addDefaultActions()
                        .setDescription(
                                "You are in the wild forest glade and you can feel great power...")
                        .addAction(
                                "investigate",
                                new InvestigateAction(
                                        "In the middle of glade you spot something shinny."
                                                + "You are drawn to this object by the power of magic..."
                                                + "Now you are sure that it is amulet of eternal love!"
                                                + "(There is no one here)"))
                        .addAction("go", new GoAction(Map.of("straight", dungeonEntrance)))
                        .addAction("pick", new PickupAction(gladeItems))
                        .build();

        shamanCabin =
                new LocationBuilder(shamanCabin)
                        .addDefaultActions()
                        .setDescription(
                                "You are in the strange cabin... Walking in you feel"
                                        + "overwhelmed by the mysterious atmosphere. Severed human heads, dried plants and"
                                        + "various mixtures are all over the place. Scents are extremely intense."
                                        + "Suddenly you are blinded by the grate light and you can see creature"
                                        + "walking out of it.")
                        .addAction("talk", new TalkAction(shamanCabin))
                        .addAction(
                                "investigate",
                                new InvestigateAction(
                                        "You are blinded! You can't see anything!"
                                                + "(Creatures around you: Thaddeus)"))
                        .addAction("go", new GoAction(Map.of("straight", dungeonEntrance)))
                        .addNPC(shaman)
                        .build();

        medicalCabin =
                new LocationBuilder(medicalCabin)
                        .addDefaultActions()
                        .setDescription(
                                "You woke up with horrible headache... You see that someone leans over you.")
                        .addAction("talk", new TalkAction(medicalCabin))
                        .addAction(
                                "investigate",
                                new InvestigateAction(
                                        "You are in the cabin... all you can see is various mixtures and medical tools."
                                                + "(Creatures around you: Britt)"))
                        .addAction("go", new GoAction(Map.of("straight", dungeonEntrance)))
                        .addNPC(medical)
                        .build();

        dungeonEntrance =
                new LocationBuilder(dungeonEntrance)
                        .addDefaultActions()
                        .setDescription(
                                "You are in front of the dungeon. If you choose to walk in"
                                        + "you have to cross the devastated bridge... There is a risk that you can fall!")
                        .addAction(
                                "investigate",
                                new InvestigateAction(
                                        "What are you looking at?"
                                                + "The bridge won't cross itself!"
                                                + "(There is no one here)"))
                        .addAction("go", new DungeonEntranceGoAction(Map.of("straight", dungeon)))
                        .build();

        dungeon =
                new LocationBuilder(dungeon)
                        .addDefaultActions()
                        .setDescription(
                                "You successfully pass through the bridge! Now you are in the dungeon "
                                        + "and you can see nothing but the darkness. (You have to use your torch).")
                        .addAction(
                                "investigate",
                                new DungeonInvestigateAction(
                                        "Your torch allows you to see drawings and symbols on the walls,"
                                                + "pillaged and wrecked by time itself... Further ahead are two paths."
                                                + "(There is no one here)"))
                        .addAction(
                                "go",
                                new GoAction(
                                        Map.of(
                                                "left", trap,
                                                "right", chamberOfWealth)))
                        .build();

        trap =
                new LocationBuilder(trap)
                        .addDefaultActions()
                        .setDescription(
                                "Walking into the room you feel the rock "
                                        + "moving under your foot. You have activated a trap!"
                                        + "Poisoned arrow hits you in the arm and you feel awful "
                                        + "pain.")
                        .addActionOnEnter(new TrapEntranceRemoveHealthAction())
                        .addAction(
                                "investigate",
                                new InvestigateAction(
                                        "You feel too weak to look around..."
                                                + "(There is no one here)"))
                        .addAction("go", new GoAction(Map.of("straight", dungeonEgress)))
                        .build();

        chamberOfWealth =
                new LocationBuilder(chamberOfWealth)
                        .addDefaultActions()
                        .setDescription(
                                "You are walking into the room and you can't "
                                        + "believe what you see! Chamber is full of gold!")
                        .addAction(
                                "investigate",
                                new InvestigateAction(
                                        "You notice that treasure had to be stuck here for hundreds years..."
                                                + "(There is no one here)"))
                        .addAction("go", new GoAction(Map.of("straight", dungeonEgress)))
                        .build();

        dungeonEgress =
                new LocationBuilder(dungeonEgress)
                        .addDefaultActions()
                        .setDescription(
                                "You see end of the dungeon... "
                                        + "Suddenly you spot two enemies approaching!")
                        .addAction("talk", new TalkAction(dungeonEgress))
                        .addAction(
                                "investigate",
                                new InvestigateAction(
                                        "You can't see any useful objects around you "
                                                + "you have to deal with them with what you got!"
                                                + "(Creatures around you: Marc, Hubhert)"))
                        .addAction("go", new GoAction(Map.of("straight", village)))
                        .addNPC(beast1)
                        .addNPC(beast2)
                        .build();

        village =
                new LocationBuilder(village)
                        .addDefaultActions()
                        .setDescription(
                                "After darkness of the dungeon there is nothing better in here...")
                        .addAction(
                                "investigate",
                                new InvestigateAction(
                                        "In front of you devastated lands stretching out for several "
                                                + "hundred kilometers across but you also spot the inn and the stable."
                                                + "(There is no one here)"))
                        .addAction(
                                "go",
                                new GoAction(
                                        Map.of(
                                                "stable", stable,
                                                "inn", inn,
                                                "tavern", inn,
                                                "devastated lands", devastatedLands)))
                        .build();

        inn =
                new LocationBuilder(inn)
                        .addDefaultActions()
                        .setDescription(
                                "You are in the inn. It is a building of stone walls, "
                                        + "with several stained glass windows. Accomodations consist "
                                        + "of several large rooms with beds and woolen mattresses.")
                        .addAction("talk", new TalkAction(inn))
                        .addAction(
                                "investigate",
                                new InvestigateAction(
                                        "The innkeeper is a short female. Room is full of injured soldiers... "
                                                + "You can hear someone is chanting songs of the fallen lands. "
                                                + "(Creatures around you: Domenic, Raphael, Avery, Galen, Hilda, Darell)"))
                        .addAction("go", new GoAction(Map.of("straight", village)))
                        .addNPC(innNPC1)
                        .addNPC(innNPC2)
                        .addNPC(innNPC3)
                        .addNPC(innNPC4)
                        .addNPC(innNPC5)
                        .addNPC(innkeeper)
                        .addNPC(stranger)
                        .build();

        stable =
                new LocationBuilder(stable)
                        .addDefaultActions()
                        .setDescription(
                                "You are in the small stable. The place is divided into three separate stalls with horses.")
                        .addAction("talk", new TalkAction(stable))
                        .addAction(
                                "investigate",
                                new InvestigateAction(
                                        "You spot a tall man who smiles at you and "
                                                + "looks like he wants to offer something."
                                                + "(Creatures around you: Elvin)"))
                        .addAction("go", new GoAction(Map.of("straight", moat)))
                        .addNPC(horseBoy)
                        .build();

        devastatedLands =
                new LocationBuilder(devastatedLands)
                        .addDefaultActions()
                        .setDescription(
                                "There are devastated lands all around you... Every little"
                                        + "piece of ground tells you that horrible battles took place in here."
                                        + "Out of nowhere a bunch of Fasilius' servants attacks you!")
                        .addAction("talk", new TalkAction(devastatedLands))
                        .addAction(
                                "investigate",
                                new InvestigateAction(
                                        "There is no time to investigate! Hurry or you will die!"
                                                + "(Creatures around you: Lucas, Claudius, Julian, Annis)"))
                        .addAction("go", new GoAction(Map.of("straight", moat)))
                        .addNPC(warrior1)
                        .addNPC(warrior2)
                        .addNPC(warrior3)
                        .addNPC(warrior4)
                        .build();

        moat =
                new LocationBuilder(moat)
                        .addDefaultActions()
                        .setDescription(
                                "You see moat excavated around castle as part of the defensive "
                                        + "system as an obstacle immediately outside the walls.")
                        .addAction(
                                "investigate",
                                new InvestigateAction(
                                        "You are looking around and search something that could help you "
                                                + "pass the moat... you can't see anything useful but thin piece of wood. "
                                                + "You pick it up and place it across the moat. Now you can use it to go 'through'!"
                                                + "(There is no one here)"))
                        .addAction("go", new GoAction(Map.of("straight", castle)))
                        .build();

        castle =
                new LocationBuilder(castle)
                        .addDefaultActions()
                        .setDescription(
                                "This large walled castle has black stone walls and defensive earthworks, "
                                        + "and a shell keep. It appears deserted, but is inhabited by brigands led by Fasilius.")
                        .addAction("talk", new TalkAction(castle))
                        .addAction(
                                "investigate",
                                new InvestigateAction(
                                        "At the beginning of the corridor you spot a small cell full of unconscious prisoners..."
                                                + "Only three of them appear to be awake. You recognised them as mags from your village."
                                                + "A massive granite door blocked your path. Dire warning messages are all over it, "
                                                + "somehow untouched by time. You step closer to inspect it and... wait... you think "
                                                + "you can hear a whisper coming from behind the door... You can feel that "
                                                + "evil power is closer then ever... waiting on you."
                                                + "(Creatures around you: Herschel, Merlin, Donovan)"))
                        .addAction("go", new GoAction(Map.of("straight", supervillain)))
                        .addNPC(mag1)
                        .addNPC(mag2)
                        .addNPC(mag3)
                        .build();

        supervillain =
                new LocationBuilder(supervillain)
                        .addDefaultActions()
                        .setDescription(
                                "The room had that spooky looks, cobwebs everywhere, chandeliers suspended from the ceiling."
                                        + "A foul stench invaded your nostrils, You looked around to see where the smell was coming from "
                                        + "and nearly vomited at the sight, it was a rotting body. You recognised him as your friend from "
                                        + "the different clan who was also sent here to defeat Fasilius... You felt great sorrow and rage. "
                                        + "You turned around and saw him watching you... In that very moment you knew that you are ready to kill him...")
                        .addAction("talk", new TalkAction(supervillain))
                        .addAction(
                                "investigate",
                                new InvestigateAction(
                                        "You looked around but the only thing you could see were visions of all those "
                                                + "horrible things that must have happened in here..."
                                                + "(Creatures around you: Fasilius)"))
                        .addNPC(fasilius)
                        .build();

        // Add to map
        locations.put("town", town);
        locations.put("forest", forest);
        locations.put("forest glade", forestGlade);
        locations.put("shaman's cabin", shamanCabin);
        locations.put("medical", medicalCabin);
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
