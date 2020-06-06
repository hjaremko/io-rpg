package pl.uj.io.cuteanimals.action;

import java.util.List;
import java.util.Optional;
import pl.uj.io.cuteanimals.model.GameState;
import pl.uj.io.cuteanimals.model.Result;
import pl.uj.io.cuteanimals.model.interfaces.*;

public class EquipItem extends ArgumentAction {
    @Override
    public IResult execute(IPlayer player) {
        if (!getAcceptableStates().contains(player.getCurrentGameState())) {
            return new Result("This isn't the time for that.");
        }

        var joined = String.join(" ", getArgs());
        getArgs().clear();

        var toEquip = getItem(player.getEquipment().getItems(), joined);

        if (toEquip.isEmpty()) {
            return new Result("You don't have that");
        }

        var itemName = toEquip.get().getName();
        player.getEquipment().removeItem(toEquip.get());

        if (player.getArmor().putItem(toEquip.get())) {
            return new Result("You have put " + itemName + " on");
        }

        return new Result("You can't wear that");
    }

    private Optional<IItem> getItem(final List<IItem> list, final String name) {
        return list.stream().filter(o -> o.getName().equals(name)).findFirst();
    }

    @Override
    public List<GameState> getAcceptableStates() {
        return List.of(GameState.EXPLORATION);
    }
}
