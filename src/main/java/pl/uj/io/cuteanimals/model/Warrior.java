package pl.uj.io.cuteanimals.model;

import java.util.List;
import pl.uj.io.cuteanimals.action.ability.DoubleDown;

public class Warrior extends Slave {
    public Warrior() {
        super();
        this.abilities.put("double down", new DoubleDown());
    }

    @Override
    public String toString() {
        return "Warrior";
    }

    @Override
    public List<ItemClass> getAcceptedItemClasses() {
        return List.of(ItemClass.WARRIOR, ItemClass.ANY);
    }
}
