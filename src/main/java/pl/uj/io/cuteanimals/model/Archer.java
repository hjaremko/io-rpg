package pl.uj.io.cuteanimals.model;

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
}
