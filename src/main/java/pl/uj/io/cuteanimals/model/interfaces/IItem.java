package pl.uj.io.cuteanimals.model.interfaces;

/**
 * Provides methods that allow attribute handling (health, attack points etc).
 *
 * @version %I%
 * @since 0.0.1-SNAPSHOT
 */
public interface IItem {
    /**
     * Provides text describing item's name. Name is distinctive for every item.
     *
     * @return string that is item's name
     */
    String getName();

    /**
     * Provides text describing a specific element, its appearance and usage. Gives information
     * about how Player can use this item and what results can be expected after use.
     *
     * @return descriptive String
     */
    String getDescription();

    /**
     * Gives information about the size of the item, its weight in kilograms.
     *
     * @return the number which is the weight of the item
     */
    int getSize();

    /**
     * Provides information about attributes of the item, its attack and defence points etc.
     *
     * @return object of IAttribute type including all information
     */
    IAttributes getAttributes();
}
