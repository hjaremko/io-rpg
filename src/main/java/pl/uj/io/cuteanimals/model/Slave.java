package pl.uj.io.cuteanimals.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import pl.uj.io.cuteanimals.action.ability.Focus;
import pl.uj.io.cuteanimals.model.interfaces.IAction;
import pl.uj.io.cuteanimals.model.interfaces.PlayerClass;

public class Slave implements PlayerClass {
    protected final Map<String, IAction> abilities;

    public Slave() {
        this.abilities = new HashMap<>();
        this.abilities.put("focus", new Focus());
    }

    @Override
    public Map<String, IAction> getAbilities() {
        return abilities;
    }

    @Override
    public String toString() {
        return "Slave";
    }

    @Override
    public List<ItemClass> getAcceptedItemClasses() {
        return List.of(ItemClass.ANY);
    }
}
