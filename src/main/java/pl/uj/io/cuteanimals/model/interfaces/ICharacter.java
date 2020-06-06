package pl.uj.io.cuteanimals.model.interfaces;

/**
 * Provides methods to manage character.
 *
 * @version %I%
 * @since 0.0.1-SNAPSHOT
 */
public interface ICharacter {

    /**
     * Gives all character's equipment. Includes equipped objects as well as those currently in the
     * backpack.
     *
     * @return List of IEquipment type element.
     */
    IEquipment getEquipment();

    IEquipment getArmor();

    /**
     * Gives characters's current attributes taking account all bonuses.
     *
     * @return IAttributes type element.
     */
    IAttributes getAttributes();
}
