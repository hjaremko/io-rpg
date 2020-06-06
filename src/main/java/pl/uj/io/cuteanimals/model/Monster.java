package pl.uj.io.cuteanimals.model;

import pl.uj.io.cuteanimals.model.interfaces.*;

public class Monster implements ICharacter {
    private final IEquipment loots;
    private final IAttributes stats;
    private final String name;

    public Monster(String name, IAttributes stats) {
        this.loots = new Backpack();
        this.stats = stats;
        this.name = name;
    }

    @Override
    public IEquipment getEquipment() {
        return loots;
    }

    @Override
    public IEquipment getArmor() {
        return null;
    }

    @Override
    public IAttributes getAttributes() {
        return stats;
    }

    public String getName() {
        return name;
    }
}
