package pl.uj.io.cuteanimals.model.interfaces;

import java.util.List;

/**
 * Provides methods to manage equipment.
 *
 * @version %I%
 * @since 0.0.1-SNAPSHOT
 */
public interface IEquipment {

    /**
     * Returns all of the items present in the equipment.
     *
     * @return list of IItem objects.
     */
    List<IItem> getItems();


    /**
     * Adds given item to the equipment.
     *
     * @param item item to add to the equipment.
     * @return true if added, otherwise false.
     */
    boolean putItem(IItem item);

    /**
     * Removed given item from the equipment.
     *
     * @param item item to remove from the equipment.
     * @return true if removed, otherwise false.
     */
    boolean removeItem(IItem item);

    /**
     * Returns formatted description of equipment contents to show it to the player.
     *
     * @return description of equipment contents
     */
    String showItems();
}
