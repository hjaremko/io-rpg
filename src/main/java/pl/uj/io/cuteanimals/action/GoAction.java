package pl.uj.io.cuteanimals.action;

import java.util.List;
import java.util.Map;
import pl.uj.io.cuteanimals.model.GameState;
import pl.uj.io.cuteanimals.model.Result;
import pl.uj.io.cuteanimals.model.interfaces.*;

// TODO: find out if we can reduce boilerplate using FunctionalInterface like Interpreter

/**
 * Moves player to given location.
 *
 * @version %I%
 * @since 0.0.1-SNAPSHOT
 */
public class GoAction extends ArgumentAction {
    private final Map<String, ILocation> locations;

    public GoAction(Map<String, ILocation> wheres) {
        super();
        locations = wheres;
    }

    @Override
    public IResult execute(IPlayer player) {
        if (!getAcceptableStates().contains(player.getCurrentGameState())) {
            return new Result("This isn't the time for that.");
        }

        var joined = String.join(" ", getArgs());
        var toGo = locations.get(joined);
        getArgs().clear();

        if (toGo == null) {
            return new Result("You want to go... where?");
        }

        player.changeLocation(toGo);
        return new Result(toGo.getDescription());
    }

    @Override
    public List<GameState> getAcceptableStates() {
        return List.of(GameState.EXPLORATION);
    }
}
