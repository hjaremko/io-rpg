package pl.uj.io.cuteanimals.action;

import java.util.List;
import java.util.Optional;
import pl.uj.io.cuteanimals.model.GameState;
import pl.uj.io.cuteanimals.model.Result;
import pl.uj.io.cuteanimals.model.interfaces.*;

public class UnequipItem extends ArgumentAction {
    @Override
    public IResult actionBody(IPlayer player, String itemName) {
        var toUnequip = getItem(player.getArmor().getItems(), itemName);

        if (toUnequip.isEmpty()) {
            return new Result("You are not wearing that");
        }

        player.getArmor().removeItem(toUnequip.get());

        if (player.getEquipment().putItem(toUnequip.get())) {
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
