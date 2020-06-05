package pl.uj.io.cuteanimals.model.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import pl.uj.io.cuteanimals.model.GameState;
import pl.uj.io.cuteanimals.model.Result;
import pl.uj.io.cuteanimals.model.interfaces.IAction;
import pl.uj.io.cuteanimals.model.interfaces.ICharacter;
import pl.uj.io.cuteanimals.model.interfaces.IItem;
import pl.uj.io.cuteanimals.model.interfaces.IResult;

public class ThrowAwayAction implements IAction {
    private List<String> args;

    public ThrowAwayAction() {
        this.args = new ArrayList<>();
    }

    @Override
    public IResult execute(ICharacter character) {
        if (!getAcceptableStates().contains(character.getCurrentGameState())) {
            return new Result("This isn't the time for that.");
        }

        var joined = String.join(" ", args);
        args.clear();

        var toThrow = getItem(character.getEquipment().getItems(), joined);

        if (toThrow.isEmpty()) {
            return new Result("You don't have that");
        }

        var itemName = toThrow.get().getName();
        character.getEquipment().removeItem(toThrow.get());
        return new Result("You have thrown " + itemName + " away");
    }

    @Override
    public List<String> getArgs() {
        return args;
    }

    @Override
    public void setArgs(List<String> args) {
        this.args = args;
    }

    private Optional<IItem> getItem(final List<IItem> list, final String name) {
        return list.stream().filter(o -> o.getName().equals(name)).findFirst();
    }

    @Override
    public List<GameState> getAcceptableStates() {
        return List.of(GameState.EXPLORATION);
    }
}
