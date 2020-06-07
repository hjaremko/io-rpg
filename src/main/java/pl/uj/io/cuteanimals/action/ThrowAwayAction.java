package pl.uj.io.cuteanimals.action;

import java.util.List;
import java.util.Optional;
import pl.uj.io.cuteanimals.model.GameState;
import pl.uj.io.cuteanimals.model.Result;
import pl.uj.io.cuteanimals.model.interfaces.*;

/**
 * Provides methods that let the Player get rid of his items.
 *
 * @version %I%
 * @since 0.0.1-SNAPSHOT
 */
public class ThrowAwayAction extends ArgumentAction {
    @Override
    public IResult actionBody(IPlayer player, String itemName) {
        var toThrow = getItem(player.getEquipment().getItems(), itemName);

        if (toThrow.isEmpty()) {
            return new Result("You don't have that");
        }

        player.getEquipment().removeItem(toThrow.get());
        return new Result("You have thrown " + itemName + " away");
    }

    private Optional<IItem> getItem(final List<IItem> list, final String name) {
        return list.stream().filter(o -> o.getName().toLowerCase().equals(name)).findFirst();
    }

    @Override
    public List<GameState> getAcceptableStates() {
        return List.of(GameState.EXPLORATION);
    }
}
