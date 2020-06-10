/**
 * Provides methods that describe specific location (available actions, items, NPCs etc).
 *
 * @version %I%
 * @since 0.0.1-SNAPSHOT
 */
package pl.uj.io.cuteanimals.model.interfaces;

import java.util.List;
import java.util.Map;
import pl.uj.io.cuteanimals.model.NPC;

public interface ILocation {

    /**
     * Gives text describing current location, what can be seen by the Player at the first glance.
     * Gives a general idea of how the location looks like.
     *
     * @return descriptive String
     */
    String getDescription();

    /**
     * Gives a list of actions that the Player can execute in the current location, such as starting
     * a fight or arrange a conversation. Considers current state of the Player (equipment,
     * attributes).
     *
     * @return list of elements of IAction type.
     */
    Map<String, IAction> getAvailableActions();

    /**
     * Gives a list of non-playable-characters that are currently in the Location that the Player
     * can integrate with (start a fight, conversation, buy something).
     *
     * @return list of elements of NPC type.
     */
    List<NPC> getNPCs();

    /**
     * Gives a list of items that are currently in the Location that the Player can integrate with
     * (take them, use them, drink/eat them etc.).
     *
     * @return IEquipment containing items.
     */
    IEquipment getItems();

    /**
     * Lets location execute any action on player when entering it.
     *
     * @param player player entering location
     */
    IResult onEnter(IPlayer player);

    /**
     * Gives an action that is executed on player entering location.
     *
     * @return action executed on player entering location
     */
    IAction getActionOnEnter();
}
