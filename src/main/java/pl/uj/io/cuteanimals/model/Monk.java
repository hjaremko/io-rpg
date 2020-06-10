package pl.uj.io.cuteanimals.model;

import java.util.List;
import pl.uj.io.cuteanimals.action.ability.Heal;

public class Monk extends Slave {
    public Monk() {
        super();
        this.abilities.put("heal", new Heal());
    }

    @Override
    public String toString() {
        return "Monk";
    }

    @Override
    public List<ItemClass> getAcceptedItemClasses() {
        return List.of(ItemClass.MONK, ItemClass.ANY);
    }
}
