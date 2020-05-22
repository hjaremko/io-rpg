package pl.uj.io.cuteanimals.model.action;

import java.util.List;
import pl.uj.io.cuteanimals.model.Result;
import pl.uj.io.cuteanimals.model.interfaces.IAction;
import pl.uj.io.cuteanimals.model.interfaces.ICharacter;

/**
 * Prints Location's "look around" message.
 *
 * @version %I%
 * @since 0.0.1-SNAPSHOT
 */
public class InvestigateAction implements IAction {
    // TODO: argumentless actions
    @Override
    public List<String> getArgs() {
        return null;
    }

    @Override
    public void setArgs(List<String> unused) {
        // Investigate is argumentless.
    }

    @Override
    public Result execute(ICharacter character) {
        // TODO: fetch current player location and return its "look around" method
        return new Result("Looking around null");
    }
}
