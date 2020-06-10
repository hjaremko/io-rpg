package pl.uj.io.cuteanimals.action;

import java.util.List;
import java.util.Optional;
import pl.uj.io.cuteanimals.model.GameState;
import pl.uj.io.cuteanimals.model.ItemType;
import pl.uj.io.cuteanimals.model.Result;
import pl.uj.io.cuteanimals.model.interfaces.ArgumentAction;
import pl.uj.io.cuteanimals.model.interfaces.IItem;
import pl.uj.io.cuteanimals.model.interfaces.IPlayer;
import pl.uj.io.cuteanimals.model.interfaces.IResult;

public class UseAction extends ArgumentAction {
    @Override
    public IResult actionBody(IPlayer player, String itemName) {
        var toUse = getItem(player.getEquipment().getItems(), itemName);

        if (toUse.isEmpty()) {
            return new Result("You don't have that.");
        }

        if (!toUse.get().getType().equals(ItemType.USABLE)) {
            return new Result("You can't use that.");
        }

        return player.use(toUse.get());
    }

    @Override
    public List<GameState> getAcceptableStates() {
        return List.of(GameState.EXPLORATION, GameState.FIGHT);
    }

    private Optional<IItem> getItem(final List<IItem> list, final String name) {
        return list.stream().filter(o -> o.getName().toLowerCase().equals(name)).findFirst();
    }
}
