package pl.uj.io.cuteanimals.action;

import java.util.List;
import pl.uj.io.cuteanimals.model.GameState;
import pl.uj.io.cuteanimals.model.Result;
import pl.uj.io.cuteanimals.model.interfaces.ArgumentAction;
import pl.uj.io.cuteanimals.model.interfaces.IPlayer;
import pl.uj.io.cuteanimals.model.interfaces.IResult;

public class CastAction extends ArgumentAction {
    @Override
    protected IResult actionBody(IPlayer player, String spellName) {
        var toCast = player.getAbilities().get(spellName);

        if (toCast == null) {
            return new Result("You don't know how to do '" + spellName + "'.");
        }

        return toCast.execute(player);
    }

    @Override
    public List<GameState> getAcceptableStates() {
        return List.of(GameState.FIGHT);
    }
}
