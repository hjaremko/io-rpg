package pl.uj.io.cuteanimals.model;

import java.util.ArrayList;
import java.util.List;
import pl.uj.io.cuteanimals.model.interfaces.IEquipment;
import pl.uj.io.cuteanimals.model.interfaces.IItem;

public class ArmorBackpack implements IEquipment {
    IItem weapon;
    IItem armor;

    @Override
    public List<IItem> getItems() {
        var eq = new ArrayList<IItem>();

        if (weapon != null) {
            eq.add(weapon);
        }

        if (armor != null) {
            eq.add(armor);
        }

        return eq;
    }

    @Override
    public boolean putItem(IItem item) {
        if (item.getType() == ItemType.WEAPON && weapon == null) {
            this.weapon = item;
            // TODO: increase stats or calculate them during battle
            return true;
        }

        if (item.getType() == ItemType.ARMOR && armor == null) {
            this.armor = item;
            // TODO: increase stats or calculate them during battle
            return true;
        }

        return false;
    }

    @Override
    public boolean removeItem(IItem item) {
        if (weapon == item) {
            weapon = null;
            return true;
        }

        if (armor == item) {
            armor = null;
            return true;
        }

        return false;
    }

    @Override
    public String showItems() {
        return getItems().toString();
    }
}
