package pl.uj.io.cuteanimals.action;

import java.util.List;
import pl.uj.io.cuteanimals.model.GameState;
import pl.uj.io.cuteanimals.model.Result;
import pl.uj.io.cuteanimals.model.interfaces.ArgumentAction;
import pl.uj.io.cuteanimals.model.interfaces.ICharacter;
import pl.uj.io.cuteanimals.model.interfaces.IResult;

/**
 * Just printing action, helps parsing and may be used for printing dialogs.
 *
 * @version %I%
 * @since 0.0.1-SNAPSHOT
 */
public class MessageAction extends ArgumentAction {
    @Override
    public IResult execute(ICharacter character) {
        return new Result(getArgs().toString());
    }

    @Override
    public List<GameState> getAcceptableStates() {
        return List.of(GameState.EXPLORATION, GameState.FIGHT);
    }
}
