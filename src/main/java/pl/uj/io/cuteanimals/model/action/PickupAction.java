package pl.uj.io.cuteanimals.model.action;

import java.util.List;
import java.util.Map;
import pl.uj.io.cuteanimals.model.GameState;
import pl.uj.io.cuteanimals.model.Result;
import pl.uj.io.cuteanimals.model.interfaces.*;

public class PickupAction extends ArgumentAction {
    private final Map<String, IItem> items;

    public PickupAction(Map<String, IItem> items) {
        super();
        this.items = items;
    }

    @Override
    public IResult execute(ICharacter character) {
        if (!getAcceptableStates().contains(character.getCurrentGameState())) {
            return new Result("This isn't the time for that.");
        }

        var joined = String.join(" ", getArgs());
        var toPickup = items.get(joined);
        getArgs().clear();

        if (toPickup == null) {
            return new Result("Nothing here");
        }

        if (!character.getEquipment().putItem(toPickup)) {
            return new Result("This item is too heavy!");
        }

        // TODO: after picking up gold in chamberOfWealth add money

        var itemName = toPickup.getName();
        items.remove(joined);
        return new Result("You have picked " + itemName + " up");
    }

    @Override
    public List<GameState> getAcceptableStates() {
        return List.of(GameState.EXPLORATION);
    }
}
