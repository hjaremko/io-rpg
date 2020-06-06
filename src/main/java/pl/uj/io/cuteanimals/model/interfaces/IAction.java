package pl.uj.io.cuteanimals.model.interfaces;

import java.util.List;
import pl.uj.io.cuteanimals.model.GameState;

/**
 * Provides method to perform actions.
 *
 * @version %I%
 * @since 0.0.1-SNAPSHOT
 */
public interface IAction {

    /**
     * Performs action that can e.g. modify character attributes etc.
     *
     * @param player specifies character whose state can be modified.
     * @return IResult object with information about outcome of the action.
     */
    IResult execute(IPlayer player);

    /** @return string representation of action arguments, e.g location name or item name */
    List<String> getArgs();

    /** @param args string representation of action arguments, e.g location name or item name */
    void setArgs(List<String> args);

    /**
     * @return list of states in which action can be performed e.g travelling to another location is
     *     possible only during EXPLORATION state
     */
    List<GameState> getAcceptableStates();
}
