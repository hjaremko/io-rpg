package pl.uj.io.cuteanimals.action;

import java.util.List;
import pl.uj.io.cuteanimals.model.GameState;
import pl.uj.io.cuteanimals.model.PlayerBackpack;
import pl.uj.io.cuteanimals.model.Result;
import pl.uj.io.cuteanimals.model.interfaces.ArgumentlessAction;
import pl.uj.io.cuteanimals.model.interfaces.IPlayer;
import pl.uj.io.cuteanimals.model.interfaces.IResult;

public class ShowBackpack extends ArgumentlessAction {
    @Override
    public IResult execute(IPlayer player) {
        if (!getAcceptableStates().contains(player.getCurrentGameState())) {
            return new Result("This isn't the time for that.");
        }

        var spaceLeft =
                "Space left: "
                        + ((PlayerBackpack) player.getEquipment()).getRemainingCapacity()
                        + "\n";
        return new Result(spaceLeft + player.getEquipment().showItems());
    }

    @Override
    public List<GameState> getAcceptableStates() {
        return List.of(GameState.EXPLORATION, GameState.FIGHT);
    }
}
