package pl.uj.io.cuteanimals.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import pl.uj.io.cuteanimals.model.interfaces.*;

/**
 * Provides methods to manage any Location.
 *
 * @version %I%
 * @since 0.0.1-SNAPSHOT
 */
public class DefaultLocation implements ILocation {
    protected String description;
    protected Map<String, IAction> actionMap;
    protected List<NPC> npcList;
    protected List<IEquipment> equipmentList;
    protected IAction actionOnEnter;

    public DefaultLocation() {
        this.description = "";
        this.actionMap = new HashMap<>();
        this.npcList = new ArrayList<>();
        this.equipmentList = new ArrayList<>();
        this.actionOnEnter = null;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Map<String, IAction> getAvailableActions() {
        return actionMap;
    }

    public void setAvailableActions(Map<String, IAction> availableActions) {
        this.actionMap = availableActions;
    }

    @Override
    public List<NPC> getNPCs() {
        return npcList;
    }

    public void setNPCs(List<NPC> npcList) {
        this.npcList = npcList;
    }

    @Override
    public List<IEquipment> getItems() {
        return equipmentList;
    }

    public void setItems(List<IEquipment> items) {
        this.equipmentList = items;
    }

    @Override
    public IAction getActionOnEnter() {
        return actionOnEnter;
    }

    public void setActionOnEnter(IAction actionOnEnter) {
        this.actionOnEnter = actionOnEnter;
    }

    @Override
    public IResult onEnter(IPlayer player) {
        if (actionOnEnter != null) {
            return actionOnEnter.execute(player);
        }
        return new Result("");
    }
}
