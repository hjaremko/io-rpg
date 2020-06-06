package pl.uj.io.cuteanimals.action;

import java.util.List;
import pl.uj.io.cuteanimals.model.GameState;
import pl.uj.io.cuteanimals.model.PlayerBackpack;
import pl.uj.io.cuteanimals.model.Result;
import pl.uj.io.cuteanimals.model.interfaces.ArgumentlessAction;
import pl.uj.io.cuteanimals.model.interfaces.ICharacter;
import pl.uj.io.cuteanimals.model.interfaces.IResult;

public class ShowBackpack extends ArgumentlessAction {
    @Override
    public IResult execute(ICharacter character) {
        if (!getAcceptableStates().contains(character.getCurrentGameState())) {
            return new Result("This isn't the time for that.");
        }

        var spaceLeft =
                "Space left: "
                        + ((PlayerBackpack) character.getEquipment()).getRemainingCapacity()
                        + "\n";
        return new Result(spaceLeft + character.getEquipment().showItems());
    }

    @Override
    public List<GameState> getAcceptableStates() {
        return List.of(GameState.EXPLORATION, GameState.FIGHT);
    }
}
