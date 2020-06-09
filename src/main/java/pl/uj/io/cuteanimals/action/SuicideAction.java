package pl.uj.io.cuteanimals.action;

import java.util.List;
import pl.uj.io.cuteanimals.model.Color;
import pl.uj.io.cuteanimals.model.GameState;
import pl.uj.io.cuteanimals.model.fight.FightLog;
import pl.uj.io.cuteanimals.model.interfaces.ArgumentlessAction;
import pl.uj.io.cuteanimals.model.interfaces.IPlayer;
import pl.uj.io.cuteanimals.model.interfaces.IResult;

public class SuicideAction extends ArgumentlessAction {
    @Override
    protected IResult actionBody(IPlayer player) {
        player.getAttributes().addHealth(-player.getAttributes().getHealth());
        return new FightLog(player.toString() + " killed himself.", Color.RED);
    }

    @Override
    public List<GameState> getAcceptableStates() {
        return List.of(GameState.EXPLORATION, GameState.FIGHT);
    }
}
