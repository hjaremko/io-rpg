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
        if (weapon != null || armor != null) {
            stringBuilder.append("You wear the following items: ");
        }
        if (weapon != null) {
            stringBuilder
                    .append("\n \n")
                    .append("Left hand: ")
                    .append(weapon.getName())
                    .append("\n")
                    .append("Description: ")
                    .append(weapon.getDescription())
                    .append(", Type: ")
                    .append(weapon.getType().toString())
                    .append(", Size: ")
                    .append(weapon.getSize())
                    .append(", ")
                    .append(weapon.getAttributes().toString());
        }
        if (armor != null) {
            stringBuilder
                    .append("\n \n")
                    .append("Right hand: ")
                    .append(armor.getName())
                    .append("\n")
                    .append("Description: ")
                    .append(armor.getDescription())
                    .append(", Type: ")
                    .append(armor.getType().toString())
                    .append(", Size: ")
                    .append(armor.getSize())
                    .append(", ")
                    .append(armor.getAttributes().toString());
        }

        return stringBuilder.toString();
    }
}
