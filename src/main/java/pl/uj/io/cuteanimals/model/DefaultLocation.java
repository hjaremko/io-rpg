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
    // TODO: remove probably
    protected IEquipment equipment;
    protected IAction actionOnEnter;

    public DefaultLocation() {
        this.description = "";
        this.actionMap = new HashMap<>();
        this.npcList = new ArrayList<>();
        this.equipment = new Backpack();
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
    public IEquipment getItems() {
        return equipment;
    }

    public void setItems(IEquipment items) {
        this.equipment = items;
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
