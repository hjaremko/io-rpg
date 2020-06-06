package pl.uj.io.cuteanimals.model;

import java.util.ArrayList;
import java.util.List;
import pl.uj.io.cuteanimals.model.interfaces.ICharacter;
import pl.uj.io.cuteanimals.model.interfaces.IEquipment;
import pl.uj.io.cuteanimals.model.interfaces.IItem;

public class ArmorBackpack implements IEquipment {
    private final ICharacter owner;
    private IItem weapon;
    private IItem armor;

    public ArmorBackpack(ICharacter owner) {
        this.owner = owner;
    }

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
        if (item.getAttributes().getLevel() > owner.getAttributes().getLevel()) {
            return false;
        }

        if (item.getType() == ItemType.WEAPON && weapon == null) {
            this.weapon = item;
            return true;
        }

        if (item.getType() == ItemType.ARMOR && armor == null) {
            this.armor = item;
            return true;
        }

        return false;
    }

    @Override
    public boolean removeItem(IItem item) {
        if (weapon.equals(item)) {
            weapon = null;
            return true;
        }

        if (armor.equals(item)) {
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
