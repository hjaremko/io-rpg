package pl.uj.io.cuteanimals.model;

import java.util.ArrayList;
import java.util.List;
import pl.uj.io.cuteanimals.model.interfaces.ICharacter;
import pl.uj.io.cuteanimals.model.interfaces.IItem;

public class PlayerBackpack extends Backpack {
    private final List<IItem> backpack = new ArrayList<>();
    private final ICharacter owner;
    private int currentWeight = 0;

    public PlayerBackpack(ICharacter owner) {
        this.owner = owner;
    }

    @Override
    public List<IItem> getItems() {
        return backpack;
    }

    @Override
    public boolean putItem(IItem item) {
        if (item.getSize() > getRemainingCapacity()) {
            return false;
        }

        backpack.add(item);
        currentWeight += item.getSize();
        return true;
    }

    @Override
    public boolean removeItem(IItem item) {
        backpack.remove(item);
        currentWeight -= item.getSize();
        // TODO: check of possible (e.g. soul bounded items?)
        return true;
    }

    @Override
    public String showItems() {
        return backpack.toString();
    }

    private int getCapacity() {
        // The stronger a player is, the more he can carry
        int baseCapacity = 10;
        return baseCapacity + 3 * owner.getAttributes().getAttack();
    }

    private int getRemainingCapacity() {
        return getCapacity() - currentWeight;
    }
}
