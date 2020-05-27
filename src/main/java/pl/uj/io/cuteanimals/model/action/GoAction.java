package pl.uj.io.cuteanimals.model.action;

import java.util.ArrayList;
import java.util.List;
import pl.uj.io.cuteanimals.model.Result;
import pl.uj.io.cuteanimals.model.interfaces.IAction;
import pl.uj.io.cuteanimals.model.interfaces.ICharacter;
import pl.uj.io.cuteanimals.model.interfaces.IResult;

// TODO: find out if we can reduce boilerplate using FunctionalInterface like Interpreter

/**
 * Moves player to given location.
 *
 * @version %I%
 * @since 0.0.1-SNAPSHOT
 */
public class GoAction implements IAction {
    private List<String> where;

    public GoAction() {
        this.where = new ArrayList<>();
    }

    public GoAction(List<String> where) {
        this.where = where;
    }

    @Override
    public List<String> getArgs() {
        return where;
    }

    @Override
    public void setArgs(List<String> where) {
        this.where = where;
    }

    @Override
    public IResult execute(ICharacter character) {
        // TODO: fetch location object matching `where` and move player there,
        //  if location does not exist return suitable message
        return new Result("Going to " + where.toString());
    }
}
