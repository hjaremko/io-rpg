package pl.uj.io.cuteanimals.model;

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
}
