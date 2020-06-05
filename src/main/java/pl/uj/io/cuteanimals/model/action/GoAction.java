package pl.uj.io.cuteanimals.model.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import pl.uj.io.cuteanimals.model.GameState;
import pl.uj.io.cuteanimals.model.Result;
import pl.uj.io.cuteanimals.model.interfaces.IAction;
import pl.uj.io.cuteanimals.model.interfaces.ICharacter;
import pl.uj.io.cuteanimals.model.interfaces.ILocation;
import pl.uj.io.cuteanimals.model.interfaces.IResult;

// TODO: find out if we can reduce boilerplate using FunctionalInterface like Interpreter

/**
 * Moves player to given location.
 *
 * @version %I%
 * @since 0.0.1-SNAPSHOT
 */
public class GoAction implements IAction {
    private final Map<String, ILocation> locations;
    private List<String> args;

    public GoAction(Map<String, ILocation> wheres) {
        locations = wheres;
        args = new ArrayList<>();
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
    public IResult execute(ICharacter character) {
        if (!getAcceptableStates().contains(character.getCurrentGameState())) {
            return new Result("This isn't the time for that.");
        }

        var joined = String.join(" ", args);
        var toGo = locations.get(joined);
        args.clear();

        if (toGo == null) {
            return new Result("You want to go... where?");
        }

        character.changeLocation(toGo);
        return new Result(toGo.getDescription());
    }

    @Override
    public List<GameState> getAcceptableStates() {
        return List.of(GameState.EXPLORATION);
    }
}
