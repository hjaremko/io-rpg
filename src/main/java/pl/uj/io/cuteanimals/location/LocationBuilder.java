package pl.uj.io.cuteanimals.location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import pl.uj.io.cuteanimals.action.*;
import pl.uj.io.cuteanimals.model.Backpack;
import pl.uj.io.cuteanimals.model.DefaultLocation;
import pl.uj.io.cuteanimals.model.NPC;
import pl.uj.io.cuteanimals.model.interfaces.IAction;
import pl.uj.io.cuteanimals.model.interfaces.IEquipment;
import pl.uj.io.cuteanimals.model.interfaces.IItem;

/** Helper class used to build locations during WorldMap initialization */
public class LocationBuilder {
    private final DefaultLocation location;
    private final Map<String, IAction> actionMap;
    private final List<NPC> npcList;
    private final IEquipment equipment;
    private String description;
    private IAction actionOnEnter;

    public LocationBuilder(DefaultLocation location) {
        this.description = "";
        this.actionMap = new HashMap<>();
        this.npcList = new ArrayList<>();
        this.equipment = new Backpack();
        this.actionOnEnter = null;
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
        this.actionMap.put("skills", new ShowAbilities());
        this.actionMap.put("eq", new ShowArmor());
        this.actionMap.put("attack", new StandardAttack());
        this.actionMap.put("block", new Block());
        this.actionMap.put("use", new UseAction());
        this.actionMap.put("cast", new CastAction());
        this.actionMap.put("suicide", new SuicideAction());
        return this;
    }

    public LocationBuilder addStartingActions() {
        this.actionMap.put("magician", new PickMagician());
        this.actionMap.put("warrior", new PickWarrior());
        this.actionMap.put("archer", new PickArcher());
        return this;
    }

    public LocationBuilder addNPC(@NotNull NPC npc) {
        this.npcList.add(npc);
        return this;
    }

    public LocationBuilder addItem(@NotNull IItem item) {
        this.equipment.putItem(item);
        return this;
    }

    public LocationBuilder addActionOnEnter(@NotNull IAction action) {
        this.actionOnEnter = action;
        return this;
    }

    public DefaultLocation build() {
        location.setDescription(description);
        location.setAvailableActions(actionMap);
        location.setItems(equipment);
        location.setNPCs(npcList);
        location.setActionOnEnter(actionOnEnter);

        return location;
    }
}
