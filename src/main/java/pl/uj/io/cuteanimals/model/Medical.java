package pl.uj.io.cuteanimals.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import pl.uj.io.cuteanimals.model.interfaces.IAction;
import pl.uj.io.cuteanimals.model.interfaces.IEquipment;
import pl.uj.io.cuteanimals.model.interfaces.ILocation;

public class Medical implements ILocation {
    private Map<String, IAction> availableActions;

    private List<NPC> npcList;

    public Medical() {
        this.availableActions = new HashMap<>();
        this.npcList = new ArrayList<>();
    }

    @Override
    public String getDescription() {
        return "You woke up with horrible headache... You see that someone leans over you.";
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
