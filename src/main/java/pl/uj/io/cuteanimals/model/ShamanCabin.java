package pl.uj.io.cuteanimals.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import pl.uj.io.cuteanimals.model.interfaces.IAction;
import pl.uj.io.cuteanimals.model.interfaces.IEquipment;
import pl.uj.io.cuteanimals.model.interfaces.ILocation;

public class ShamanCabin implements ILocation {
    private final Map<String, IAction> availableActions;
    private final List<NPC> npcList;

    public ShamanCabin() {
        this.availableActions = new HashMap<>();
        this.npcList = new ArrayList<>();
    }

    @Override
    public String getDescription() {
        return "You are in the strange cabin... Walking in you feel"
                + "overwhelmed by the mysterious atmosphere. Severed human heads, dried plants and"
                + "various mixtures are all over the place. Scents are extremely intense."
                + "Suddenly you are blinded by the grate light and you can see creature"
                + "walking out of it.";
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
