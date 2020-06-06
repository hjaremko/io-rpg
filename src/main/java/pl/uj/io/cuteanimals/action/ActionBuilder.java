package pl.uj.io.cuteanimals.action;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;
import pl.uj.io.cuteanimals.model.interfaces.IAction;

/** Helper class used to build actions during interpreting */
public class ActionBuilder {
    private IAction action;
    private List<String> args;

    public ActionBuilder() {
        this.args = new ArrayList<>();
    }

    public ActionBuilder(@NotNull IAction action, @NotNull List<String> args) {
        this.action = action;
        this.args = args;
    }

    public ActionBuilder addAction(@NotNull IAction action) {
        this.action = action;
        args = action.getArgs();
        return this;
    }

    public ActionBuilder addArgs(@NotNull List<String> arg) {
        args.addAll(0, arg);
        return this;
    }

    public IAction collect() {
        action.setArgs(args);
        return action;
    }
}
