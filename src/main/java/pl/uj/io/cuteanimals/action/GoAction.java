package pl.uj.io.cuteanimals.action;

import java.util.List;
import java.util.Map;
import pl.uj.io.cuteanimals.model.GameState;
import pl.uj.io.cuteanimals.model.Result;
import pl.uj.io.cuteanimals.model.interfaces.ContainerArgumentAction;
import pl.uj.io.cuteanimals.model.interfaces.ILocation;
import pl.uj.io.cuteanimals.model.interfaces.IPlayer;
import pl.uj.io.cuteanimals.model.interfaces.IResult;

// TODO: find out if we can reduce boilerplate using FunctionalInterface like Interpreter

/**
 * Moves player to given location.
 *
 * @version %I%
 * @since 0.0.1-SNAPSHOT
 */
public class GoAction extends ContainerArgumentAction<ILocation> {
    public GoAction(Map<String, ILocation> wheres) {
        super(wheres);
    }

    @Override
    public IResult actionBody(IPlayer player, String toGoName) {
        var toGo = objects.get(toGoName);

        if (toGo == null) {
            return new Result("You want to go... where?");
        }

        IResult enterResult = player.changeLocation(toGo);
        return new Result(toGo.getDescription() + enterResult.getMessage());
    }

    @Override
    public List<GameState> getAcceptableStates() {
        return List.of(GameState.EXPLORATION);
    }
}
