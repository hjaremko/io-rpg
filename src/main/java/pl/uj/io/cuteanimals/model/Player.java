package pl.uj.io.cuteanimals.model;

import pl.uj.io.cuteanimals.model.interfaces.*;

public class Player implements ICharacter {
    ILocation currentLocation = WorldMap.getInstance().getLocation("town");
    IEquipment armorBackpack = new ArmorBackpack();
    IEquipment backpack = new Backpack();

    @Override
    public IEquipment getEquipment() {
        return backpack;
    }

    @Override
    public IEquipment getArmor() {
        return armorBackpack;
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
        currentLocation = where;
    }

    public ILocation getCurrentLocation() {
        return currentLocation;
    }
}
