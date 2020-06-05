package pl.uj.io.cuteanimals.model.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import pl.uj.io.cuteanimals.model.GameState;
import pl.uj.io.cuteanimals.model.Result;
import pl.uj.io.cuteanimals.model.interfaces.IAction;
import pl.uj.io.cuteanimals.model.interfaces.ICharacter;
import pl.uj.io.cuteanimals.model.interfaces.IItem;
import pl.uj.io.cuteanimals.model.interfaces.IResult;

public class PickupAction implements IAction {
    private final Map<String, IItem> items;
    private List<String> args;

    public PickupAction(Map<String, IItem> items) {
        this.items = items;
        this.args = new ArrayList<>();
    }

    @Override
    public IResult execute(ICharacter character) {
        if (!getAcceptableStates().contains(character.getCurrentGameState())) {
            return new Result("This isn't the time for that.");
        }

        var joined = String.join(" ", args);
        var toPickup = items.get(joined);
        args.clear();

        if (toPickup == null) {
            return new Result("Nothing here");
        }

        if (!character.getEquipment().putItem(toPickup)) {
            return new Result("This item is too heavy!");
        }

        var itemName = toPickup.getName();
        items.remove(joined);
        return new Result("You have picked " + itemName + " up");
    }

    @Override
    public List<String> getArgs() {
        return args;
    }

    @Override
    public void setArgs(List<String> args) {
        this.args = args;
    }

    @Override
    public List<GameState> getAcceptableStates() {
        return List.of(GameState.EXPLORATION);
    }
}
