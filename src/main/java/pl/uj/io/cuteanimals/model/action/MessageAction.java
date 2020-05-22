package pl.uj.io.cuteanimals.model.action;

import java.util.ArrayList;
import java.util.List;
import pl.uj.io.cuteanimals.model.Result;
import pl.uj.io.cuteanimals.model.interfaces.IAction;
import pl.uj.io.cuteanimals.model.interfaces.ICharacter;

/**
 * Just printing action, helps parsing and may be used for printing dialogs.
 *
 * @version %I%
 * @since 0.0.1-SNAPSHOT
 */
public class MessageAction implements IAction {
    private List<String> args;

    public MessageAction() {
        this.args = new ArrayList<>();
    }

    public MessageAction(List<String> args) {
        this.args = args;
    }

    @Override
    public List<String> getArgs() {
        return args;
    }

    @Override
    public void setArgs(List<String> args) {
        this.args = args;
    }

    @Override
    public Result execute(ICharacter character) {
        return new Result(args.toString());
    }
}
