package pl.uj.io.cuteanimals.action;

import java.util.List;
import java.util.Optional;
import pl.uj.io.cuteanimals.model.GameState;
import pl.uj.io.cuteanimals.model.Result;
import pl.uj.io.cuteanimals.model.interfaces.*;

public class ThrowAwayAction extends ArgumentAction {
    @Override
    public IResult execute(IPlayer player) {
        if (!getAcceptableStates().contains(player.getCurrentGameState())) {
            return new Result("This isn't the time for that.");
        }

        var joined = String.join(" ", getArgs());
        getArgs().clear();

        var toThrow = getItem(player.getEquipment().getItems(), joined);

        if (toThrow.isEmpty()) {
            return new Result("You don't have that");
        }

        var itemName = toThrow.get().getName();
        player.getEquipment().removeItem(toThrow.get());
        return new Result("You have thrown " + itemName + " away");
    }

    private Optional<IItem> getItem(final List<IItem> list, final String name) {
        return list.stream().filter(o -> o.getName().equals(name)).findFirst();
    }

    @Override
    public List<GameState> getAcceptableStates() {
        return List.of(GameState.EXPLORATION);
    }
}
