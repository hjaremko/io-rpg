package pl.uj.io.cuteanimals.plot.locations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import pl.uj.io.cuteanimals.model.NPC;
import pl.uj.io.cuteanimals.model.interfaces.IAction;
import pl.uj.io.cuteanimals.model.interfaces.IEquipment;
import pl.uj.io.cuteanimals.model.interfaces.ILocation;

public class Inn implements ILocation {
    private final Map<String, IAction> availableActions;
    private final List<NPC> npcList;

    public Inn() {
        this.availableActions = new HashMap<>();
        npcList = new ArrayList<>();
    }

    @Override
    public void addAction(String command, IAction action) {
        availableActions.put(command, action);
    }

    @Override
    public String getDescription() {
        return "You are in the inn. It is a building of stone walls, "
                + "with several stained glass windows. Accomodations consist "
                + "of several large rooms with beds and woolen mattresses.";
    }

    @Override
    public Map<String, IAction> getAvailableActions() {
        return availableActions;
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
