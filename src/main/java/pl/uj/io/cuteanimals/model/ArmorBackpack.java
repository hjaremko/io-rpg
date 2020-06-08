package pl.uj.io.cuteanimals.model;

import java.util.ArrayList;
import java.util.List;
import pl.uj.io.cuteanimals.model.interfaces.ICharacter;
import pl.uj.io.cuteanimals.model.interfaces.IEquipment;
import pl.uj.io.cuteanimals.model.interfaces.IItem;

/**
 * Provides methods to menage Player's Armor Backpack. Allows him to put, remove and display those
 * items.
 *
 * @version %I%
 * @since 0.0.1-SNAPSHOT
 */
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
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("You wear the following items: ").append("\n \n");
        stringBuilder.append("Left hand: ").append(weapon.getName()).append("\n");
        stringBuilder.append("Description: ").append(weapon.getDescription());
        stringBuilder.append(", Type: ").append(weapon.getType().toString());
        stringBuilder.append(", Size: ").append(weapon.getSize()).append(", ");
        stringBuilder.append(weapon.getAttributes().toString());
        stringBuilder.append("\n \n");
        stringBuilder.append("Right hand: ").append(armor.getName()).append("\n");
        stringBuilder.append("Description: ").append(armor.getDescription());
        stringBuilder.append(", Type: ").append(armor.getType().toString());
        stringBuilder.append(", Size: ").append(armor.getSize()).append(", ");
        stringBuilder.append(armor.getAttributes().toString());

        return stringBuilder.toString();
    }
}
