package pl.uj.io.cuteanimals.model;

import pl.uj.io.cuteanimals.model.interfaces.IAttributes;
import pl.uj.io.cuteanimals.model.interfaces.IItem;

/**
 * Provides methods to manage player's backpack
 *
 * @version %I%
 * @since 0.2.0-SNAPSHOT
 */
public class PlayerBackpack extends Backpack {
    private final IAttributes ownerAttrs;
    private int currentWeight;

    public PlayerBackpack(IAttributes ownerAttrs) {
        super();
        this.ownerAttrs = ownerAttrs;
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
        return true;
    }

    /**
     * Gives the capacity of the player's backpack. The stronger a player is, the more he can carry.
     *
     * @return int type capacity
     */
    public int getCapacity() {
        int baseCapacity = 10;
        return baseCapacity + 3 * ownerAttrs.getAttack();
    }

    /**
     * Gives the remaining capacity of the player's backpack. Subtracts the weight of the items worn
     * from the capacity number.
     *
     * @return int type remaining capacity
     */
    public int getRemainingCapacity() {
        return getCapacity() - currentWeight;
    }
}
