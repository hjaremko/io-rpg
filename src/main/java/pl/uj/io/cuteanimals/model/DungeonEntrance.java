package pl.uj.io.cuteanimals.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import pl.uj.io.cuteanimals.model.interfaces.IAction;
import pl.uj.io.cuteanimals.model.interfaces.IEquipment;
import pl.uj.io.cuteanimals.model.interfaces.ILocation;

public class DungeonEntrance implements ILocation {
    private Map<String, IAction> availableActions;

    private List<NPC> npcList;

    public DungeonEntrance() {
        this.availableActions = new HashMap<>();
        this.npcList = new ArrayList<>();
    }

    @Override
    public String getDescription() {
        return "You are in front of the dungeon. If you choose to walk in"
                + "you have to cross the devastated bridge... There is a risk that you can fall!";
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
    public void addNPC(NPC n) {
        npcList.add(n);
    }
}
