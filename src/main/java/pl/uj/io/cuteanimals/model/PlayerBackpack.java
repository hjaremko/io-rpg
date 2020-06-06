package pl.uj.io.cuteanimals.model;

import pl.uj.io.cuteanimals.model.interfaces.ICharacter;
import pl.uj.io.cuteanimals.model.interfaces.IItem;

public class PlayerBackpack extends Backpack {
    private final ICharacter owner;
    private int currentWeight;

    public PlayerBackpack(ICharacter owner) {
        super();
        this.owner = owner;
        this.currentWeight = 0;
    }

    @Override
    public boolean putItem(IItem item) {
        if (item.getSize() > getRemainingCapacity()) {
            return false;
        }

        getItems().add(item);
        currentWeight += item.getSize();
        return true;
    }

    @Override
    public boolean removeItem(IItem item) {
        getItems().remove(item);
        currentWeight -= item.getSize();
        // TODO: check of possible (e.g. soul bounded items?)
        return true;
    }

    public int getCapacity() {
        // The stronger a player is, the more he can carry
        int baseCapacity = 10;
        return baseCapacity + 3 * owner.getAttributes().getAttack();
    }

    public int getRemainingCapacity() {
        return getCapacity() - currentWeight;
    }
}
