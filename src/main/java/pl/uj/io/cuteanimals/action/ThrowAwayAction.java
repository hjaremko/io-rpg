package pl.uj.io.cuteanimals.action;

import java.util.List;
import java.util.Optional;
import pl.uj.io.cuteanimals.model.GameState;
import pl.uj.io.cuteanimals.model.Result;
import pl.uj.io.cuteanimals.model.interfaces.*;

public class ThrowAwayAction extends ArgumentAction {
    @Override
    public IResult execute(ICharacter character) {
        if (!getAcceptableStates().contains(character.getCurrentGameState())) {
            return new Result("This isn't the time for that.");
        }

        var joined = String.join(" ", getArgs());
        getArgs().clear();

        var toThrow = getItem(character.getEquipment().getItems(), joined);

        if (toThrow.isEmpty()) {
            return new Result("You don't have that");
        }

        var itemName = toThrow.get().getName();
        character.getEquipment().removeItem(toThrow.get());
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
