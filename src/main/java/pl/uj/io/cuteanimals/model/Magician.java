package pl.uj.io.cuteanimals.model;

import pl.uj.io.cuteanimals.action.ability.Heal;

public class Magician extends Slave {
    public Magician() {
        super();
        this.abilities.put("heal", new Heal());
    }

    @Override
    public String toString() {
        return "Magician";
    }
}
