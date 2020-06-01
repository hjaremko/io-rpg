package pl.uj.io.cuteanimals.model;

import pl.uj.io.cuteanimals.model.interfaces.*;

public class NPC implements ICharacter {
    @Override
    public IEquipment getEquipment() {
        return null;
    }

    @Override
    public IEquipment getArmor() {
        return null;
    }

    @Override
    public IAttributes getAttributes() {
        return null;
    }

    @Override
    public Result use(IAction action) {
        return null;
    }

    @Override
    public void changeLocation(ILocation where) {

    }

    @Override
    public GameState getCurrentGameState() {
        return null;
    }
}
