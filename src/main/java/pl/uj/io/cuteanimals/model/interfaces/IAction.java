package pl.uj.io.cuteanimals.model.interfaces;

import java.util.List;

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
     * @param character specifies character whose state can be modified.
     * @return IResult object with information about outcome of the action.
     */
    IResult execute(ICharacter character);

    /** @return string representation of action arguments, e.g location name or item name */
    List<String> getArgs();

    /** @param args string representation of action arguments, e.g location name or item name */
    void setArgs(List<String> args);
}
