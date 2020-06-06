package pl.uj.io.cuteanimals.location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import pl.uj.io.cuteanimals.action.*;
import pl.uj.io.cuteanimals.model.NPC;
import pl.uj.io.cuteanimals.model.interfaces.DefaultLocation;
import pl.uj.io.cuteanimals.model.interfaces.IAction;
import pl.uj.io.cuteanimals.model.interfaces.IEquipment;

/** Helper class used to build locations during WorldMap initialization */
public class LocationBuilder {
    DefaultLocation location;
    String description;
    Map<String, IAction> actionMap;
    List<NPC> npcList;
    List<IEquipment> equipmentList;

    public LocationBuilder() {
        this.description = "";
        this.actionMap = new HashMap<>();
        this.npcList = new ArrayList<>();
        this.equipmentList = new ArrayList<>();
    }

    public LocationBuilder(DefaultLocation location) {
        this();
        this.location = location;
    }

    public LocationBuilder setDescription(@NotNull String description) {
        this.description = description;
        return this;
    }

    public LocationBuilder addAction(@NotBlank String key, @NotNull IAction action) {
        this.actionMap.put(key, action);
        return this;
    }

    public LocationBuilder addDefaultActions() {
        this.actionMap.put("backpack", new ShowBackpack());
        this.actionMap.put("throw", new ThrowAwayAction());
        this.actionMap.put("equip", new EquipItem());
        this.actionMap.put("off", new UnequipItem());
        this.actionMap.put("stats", new ShowStats());
        this.actionMap.put("eq", new ShowArmor());
        return this;
    }

    public LocationBuilder addNPC(@NotNull NPC npc) {
        this.npcList.add(npc);
        return this;
    }

    public LocationBuilder addItem(@NotNull IEquipment equipment) {
        this.equipmentList.add(equipment);
        return this;
    }

    public DefaultLocation build() {
        location.setDescription(description);
        location.setAvailableActions(actionMap);
        location.setItems(equipmentList);
        location.setNPCs(npcList);
        return location;
    }
}
