package pl.uj.io.cuteanimals.model.interfaces;

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
     */
    void execute(ICharacter character);
}
