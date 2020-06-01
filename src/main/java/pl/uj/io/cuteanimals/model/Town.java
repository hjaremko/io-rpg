package pl.uj.io.cuteanimals.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import pl.uj.io.cuteanimals.model.action.InvestigateAction;
import pl.uj.io.cuteanimals.model.interfaces.IAction;
import pl.uj.io.cuteanimals.model.interfaces.ICharacter;
import pl.uj.io.cuteanimals.model.interfaces.IEquipment;
import pl.uj.io.cuteanimals.model.interfaces.ILocation;

public class Town implements ILocation {
    Map<String, IAction> availableActions;

    public Town() {
        this.availableActions = new HashMap<>();
        availableActions.put(
                "investigate",
                new InvestigateAction("Looking around you catch a glimpse of small inn. "));
    }

    @Override
    public void addAction(String command, IAction action) {
        availableActions.put(command, action);
    }

    @Override
    public String getDescription() {
        return "You are in the Town. What do you want to do?";
    }

    @Override
    public Map<String, IAction> getAvailableActions() {
        return availableActions;
    }

    @Override
    public List<ICharacter> getNPCs() {
        return null;
    }

    @Override
    public List<IEquipment> getItems() {
        return null;
    }
}
