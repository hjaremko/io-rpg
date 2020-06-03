package pl.uj.io.cuteanimals.model.interfaces;

import pl.uj.io.cuteanimals.model.GameState;
import pl.uj.io.cuteanimals.model.Result;

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

    /**
     * Gives result of using specific action.
     *
     * @param action specifies action to execute.
     * @return Result type element.
     */
    Result use(IAction action);

    void changeLocation(ILocation where);

    GameState getCurrentGameState();
}
