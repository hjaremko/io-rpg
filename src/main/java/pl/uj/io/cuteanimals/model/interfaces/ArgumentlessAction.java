package pl.uj.io.cuteanimals.model.interfaces;

import java.util.List;
import pl.uj.io.cuteanimals.model.Result;

/**
 * Provides compatibility with ArgumentAction
 *
 * @version %I%
 * @since 0.2.0-SNAPSHOT
 */
public abstract class ArgumentlessAction implements IAction {
    /** @return string representation of action arguments, e.g location name or item name */
    @Override
    public List<String> getArgs() {
        return null;
    }

    /** @param args string representation of action arguments, e.g location name or item name */
    @Override
    public void setArgs(List<String> args) {}

    @Override
    public IResult execute(IPlayer player) {
        if (!getAcceptableStates().contains(player.getCurrentGameState())) {
            return new Result("This isn't the time for that.");
        }

        return actionBody(player);
    }

    protected abstract IResult actionBody(IPlayer player);
}
