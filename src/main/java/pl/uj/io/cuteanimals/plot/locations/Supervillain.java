package pl.uj.io.cuteanimals.plot.locations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import pl.uj.io.cuteanimals.model.NPC;
import pl.uj.io.cuteanimals.model.interfaces.IAction;
import pl.uj.io.cuteanimals.model.interfaces.IEquipment;
import pl.uj.io.cuteanimals.model.interfaces.ILocation;

public class Supervillain implements ILocation {
    private final Map<String, IAction> availableActions;
    private final List<NPC> npcList;

    public Supervillain() {
        this.availableActions = new HashMap<>();
        this.npcList = new ArrayList<>();
    }

    @Override
    public String getDescription() {
        return "The room had that spooky looks, cobwebs everywhere, chandeliers suspended from the ceiling."
                + "A foul stench invaded your nostrils, You looked around to see where the smell was coming from "
                + "and nearly vomited at the sight, it was a rotting body. You recognised him as your friend from "
                + "the different clan who was also sent here to defeat Fasilius... You felt great sorrow and rage. "
                + "You turned around and saw him watching you... In that very moment you knew that you are ready to kill him...";
    }

    @Override
    public Map<String, IAction> getAvailableActions() {
        return availableActions;
    }

    @Override
    public void addAction(String command, IAction action) {
        availableActions.put(command, action);
    }

    @Override
    public List<NPC> getNPCs() {
        return npcList;
    }

    @Override
    public List<IEquipment> getItems() {
        return null;
    }

    @Override
    public void addNPC(NPC npc) {
        npcList.add(npc);
    }
}
