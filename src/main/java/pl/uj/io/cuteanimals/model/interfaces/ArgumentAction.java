package pl.uj.io.cuteanimals.model.interfaces;

import java.util.ArrayList;
import java.util.List;
import pl.uj.io.cuteanimals.model.Result;

/**
 * Extends IAction interface with arguments
 *
 * @version %I%
 * @since 0.2.0-SNAPSHOT
 */
public abstract class ArgumentAction implements IAction {
    protected List<String> args;

    public ArgumentAction() {
        this.args = new ArrayList<>();
    }

    /** @return string representation of action arguments, e.g location name or item name */
    @Override
    public List<String> getArgs() {
        return args;
    }

    /** @param args string representation of action arguments, e.g location name or item name */
    @Override
    public void setArgs(List<String> args) {
        this.args = args;
    }

    @Override
    public IResult execute(IPlayer player) {
        if (!getAcceptableStates().contains(player.getCurrentGameState())) {
            args.clear();
            return new Result("This isn't the time for that.");
        }

        var joined = String.join(" ", args);
        args.clear();
        return actionBody(player, joined);
    }

    protected abstract IResult actionBody(IPlayer player, String object);
}
