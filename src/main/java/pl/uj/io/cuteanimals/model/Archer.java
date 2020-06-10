package pl.uj.io.cuteanimals.model;

import java.util.List;
import pl.uj.io.cuteanimals.action.ability.Bullseye;

public class Archer extends Slave {
    public Archer() {
        super();
        this.abilities.put("bullseye", new Bullseye());
    }

    @Override
    public String toString() {
        return "Archer";
    }

    @Override
    public List<ItemClass> getAcceptedItemClasses() {
        return List.of(ItemClass.ARCHER, ItemClass.ANY);
    }
}
