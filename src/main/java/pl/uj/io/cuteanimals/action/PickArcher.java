package pl.uj.io.cuteanimals.action;

import java.util.List;
import pl.uj.io.cuteanimals.model.*;
import pl.uj.io.cuteanimals.model.interfaces.ArgumentlessAction;
import pl.uj.io.cuteanimals.model.interfaces.IPlayer;
import pl.uj.io.cuteanimals.model.interfaces.IResult;

public class PickArcher extends ArgumentlessAction {
    @Override
    protected IResult actionBody(IPlayer player) {
        player.setClass(new Archer());
        player.setGameState(GameState.EXPLORATION);

        return new CompoundResult(
                List.of(
                        new Result(
                                "You are now an Archer from Clever Hares' Clan.\n"
                                        + "An extremely dangerous adventure awaits you, full of unexpected twists.\n"
                                        + "Use your eagle eye and your extraordinary bow ability to overcome any difficulties encountered.\n",
                                Color.YELLOW),
                        new Result(player.getCurrentLocation().getDescription())));
    }

    @Override
    public List<GameState> getAcceptableStates() {
        return List.of(GameState.LIMBO);
    }
}
