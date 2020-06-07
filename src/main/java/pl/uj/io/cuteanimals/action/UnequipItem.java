package pl.uj.io.cuteanimals.action;

import java.util.List;
import java.util.Optional;
import pl.uj.io.cuteanimals.model.GameState;
import pl.uj.io.cuteanimals.model.Result;
import pl.uj.io.cuteanimals.model.interfaces.ArgumentAction;
import pl.uj.io.cuteanimals.model.interfaces.ICharacter;
import pl.uj.io.cuteanimals.model.interfaces.IItem;
import pl.uj.io.cuteanimals.model.interfaces.IResult;

public class UnequipItem extends ArgumentAction {
    @Override
    public IResult execute(ICharacter character) {
        if (!getAcceptableStates().contains(character.getCurrentGameState())) {
            return new Result("This isn't the time for that.");
        }

        var joined = String.join(" ", getArgs());
        getArgs().clear();

        var toUnequip = getItem(character.getArmor().getItems(), joined);

        if (toUnequip.isEmpty()) {
            return new Result("You are not wearing that");
        }

        var itemName = toUnequip.get().getName();
        character.getArmor().removeItem(toUnequip.get());

        if (character.getEquipment().putItem(toUnequip.get())) {
            return new Result("You have took " + itemName + " off");
        }

        return new Result("You can't take off that");
    }

    private Optional<IItem> getItem(final List<IItem> list, final String name) {
        return list.stream().filter(o -> o.getName().toLowerCase().equals(name)).findFirst();
    }

    @Override
    public List<GameState> getAcceptableStates() {
        return List.of(GameState.EXPLORATION);
    }
}
