package pl.uj.io.cuteanimals.action;

import java.util.List;
import pl.uj.io.cuteanimals.model.GameState;
import pl.uj.io.cuteanimals.model.Result;
import pl.uj.io.cuteanimals.model.interfaces.ArgumentlessAction;
import pl.uj.io.cuteanimals.model.interfaces.IPlayer;
import pl.uj.io.cuteanimals.model.interfaces.IResult;

/**
 * Prints content of Character's armor backpack.
 *
 * @version %I%
 * @since 0.0.1-SNAPSHOT
 */
public class ShowArmor extends ArgumentlessAction {
    @Override
    public IResult actionBody(IPlayer player) {
        return new Result(player.getArmor().showItems());
    }

    @Override
    public List<GameState> getAcceptableStates() {
        return List.of(GameState.EXPLORATION, GameState.FIGHT);
    }
}
