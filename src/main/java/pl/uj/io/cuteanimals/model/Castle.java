package pl.uj.io.cuteanimals.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import pl.uj.io.cuteanimals.model.interfaces.IAction;
import pl.uj.io.cuteanimals.model.interfaces.IEquipment;
import pl.uj.io.cuteanimals.model.interfaces.ILocation;

public class Castle implements ILocation {
    private Map<String, IAction> availableActions;

    private List<NPC> npcList;

    public Castle() {
        this.availableActions = new HashMap<>();
        this.npcList = new ArrayList<>();
    }

    @Override
    public String getDescription() {
        return "This large walled castle has black stone walls and defensive earthworks, "
                + "and a shell keep. It appears deserted, but is inhabited by brigands led by Fasilius.";
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
