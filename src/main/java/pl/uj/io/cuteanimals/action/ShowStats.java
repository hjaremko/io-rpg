package pl.uj.io.cuteanimals.action;

import java.util.List;
import pl.uj.io.cuteanimals.model.GameState;
import pl.uj.io.cuteanimals.model.PlayerAttributes;
import pl.uj.io.cuteanimals.model.Result;
import pl.uj.io.cuteanimals.model.interfaces.ArgumentlessAction;
import pl.uj.io.cuteanimals.model.interfaces.IPlayer;
import pl.uj.io.cuteanimals.model.interfaces.IResult;

public class ShowStats extends ArgumentlessAction {
    @Override
    public IResult execute(IPlayer player) {
        if (!getAcceptableStates().contains(player.getCurrentGameState())) {
            return new Result("This isn't the time for that.");
        }

        var exp =
                "Experience: "
                        + ((PlayerAttributes) player.getAttributes()).getExperience()
                        + "/"
                        + ((PlayerAttributes) player.getAttributes()).getRequiredExperience()
                        + "\n";
        return new Result(
                exp
                        + "Health: "
                        + player.getAttributes().getHealth()
                        + "\n"
                        + "Level: "
                        + player.getAttributes().getLevel()
                        + "\n"
                        + "Attack: "
                        + player.getAttributes().getAttack()
                        + "\n"
                        + "Defence: "
                        + player.getAttributes().getDefence()
                        + "\n");
    }

    @Override
    public List<GameState> getAcceptableStates() {
        return List.of(GameState.EXPLORATION, GameState.FIGHT);
    }
}
