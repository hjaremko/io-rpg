package pl.uj.io.cuteanimals.model.interfaces;

import java.util.ArrayList;
import java.util.List;

/**
 * Extends IAction interface with arguments
 *
 * @version %I%
 * @since 0.2.0-SNAPSHOT
 */
public abstract class ArgumentAction implements IAction {
    private List<String> args;

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
}
