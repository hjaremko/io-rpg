package pl.uj.io.cuteanimals.action;

import java.util.List;
import pl.uj.io.cuteanimals.model.*;
import pl.uj.io.cuteanimals.model.interfaces.ArgumentlessAction;
import pl.uj.io.cuteanimals.model.interfaces.IPlayer;
import pl.uj.io.cuteanimals.model.interfaces.IResult;

public class PickMagician extends ArgumentlessAction {
    @Override
    protected IResult actionBody(IPlayer player) {
        player.setClass(new Magician());
        player.setGameState(GameState.EXPLORATION);

        return new CompoundResult(
                List.of(
                        new Result(
                                "You are now an Magician from Magic Squirrels' Clan.\n"
                                        + "An extremely dangerous adventure awaits you, full of unexpected twists.\n"
                                        + "Use your knowledge of magic and the ability to wield a wand to overcome any difficulties encountered.\n",
                                Color.YELLOW),
                        new Result(player.getCurrentLocation().getDescription())));
    }

    @Override
    public List<GameState> getAcceptableStates() {
        return List.of(GameState.LIMBO);
    }
}
