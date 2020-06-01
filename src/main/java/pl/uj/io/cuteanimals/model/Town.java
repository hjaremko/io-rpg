package pl.uj.io.cuteanimals.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import pl.uj.io.cuteanimals.model.interfaces.IAction;
import pl.uj.io.cuteanimals.model.interfaces.ICharacter;
import pl.uj.io.cuteanimals.model.interfaces.IEquipment;
import pl.uj.io.cuteanimals.model.interfaces.ILocation;

public class Town implements ILocation {
    Map<String, IAction> availableActions;
    List<NPC> npcs;

    public Town() {
        this.availableActions = new HashMap<>();
        this.npcs = new ArrayList<>();
        npcs.add(new NPC(null, null, null, "Chad",
                List.of("Elo byczq", "oj tak + 1")));
    }

    @Override
    public void addAction(String command, IAction action) {
        availableActions.put(command, action);
    }

    @Override
    public String getDescription() {
        return "You are in the Town. This is Town description. It should give player "
                + "a general idea of what he can do. What do you want to do?";
    }

    @Override
    public Map<String, IAction> getAvailableActions() {
        return availableActions;
    }

    @Override
    public List<NPC> getNPCs() {
        return npcs;
    }

    @Override
    public List<IEquipment> getItems() {
        return null;
    }
}
