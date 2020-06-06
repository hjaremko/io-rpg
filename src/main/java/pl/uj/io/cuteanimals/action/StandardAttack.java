package pl.uj.io.cuteanimals.action;

import java.util.List;
import pl.uj.io.cuteanimals.model.GameState;
import pl.uj.io.cuteanimals.model.interfaces.ArgumentlessAction;
import pl.uj.io.cuteanimals.model.interfaces.IPlayer;
import pl.uj.io.cuteanimals.model.interfaces.IResult;

public class StandardAttack extends ArgumentlessAction {
    @Override
    public IResult actionBody(IPlayer player) {
        return player.getFightManager().attack();
    }

    @Override
    public List<GameState> getAcceptableStates() {
        return List.of(GameState.FIGHT);
    }
}
