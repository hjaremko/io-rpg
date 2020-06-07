package pl.uj.io.cuteanimals.action;

import java.util.List;
import pl.uj.io.cuteanimals.model.GameState;
import pl.uj.io.cuteanimals.model.Result;
import pl.uj.io.cuteanimals.model.interfaces.ArgumentlessAction;
import pl.uj.io.cuteanimals.model.interfaces.IPlayer;
import pl.uj.io.cuteanimals.model.interfaces.IResult;

/**
 * Prints Character's statistics.
 *
 * @version %I%
 * @since 0.0.1-SNAPSHOT
 */
public class ShowStats extends ArgumentlessAction {
    @Override
    public IResult actionBody(IPlayer player) {
        return new Result(player.getAttributes().toString());
    }

    @Override
    public List<GameState> getAcceptableStates() {
        return List.of(GameState.EXPLORATION, GameState.FIGHT);
    }
}
