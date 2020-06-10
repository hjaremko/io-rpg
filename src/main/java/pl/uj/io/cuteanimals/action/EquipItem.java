package pl.uj.io.cuteanimals.action;

import java.util.List;
import java.util.Optional;
import pl.uj.io.cuteanimals.model.GameState;
import pl.uj.io.cuteanimals.model.Result;
import pl.uj.io.cuteanimals.model.interfaces.ArgumentAction;
import pl.uj.io.cuteanimals.model.interfaces.IItem;
import pl.uj.io.cuteanimals.model.interfaces.IPlayer;
import pl.uj.io.cuteanimals.model.interfaces.IResult;

/**
 * Provides methods to equip item from Character's backpack.
 *
 * @version %I%
 * @since 0.0.1-SNAPSHOT
 */
public class EquipItem extends ArgumentAction {
    @Override
    protected IResult actionBody(IPlayer player, String toEquipName) {
        var toEquip = getItem(player.getEquipment().getItems(), toEquipName);

        if (toEquip.isEmpty()) {
            return new Result("You don't have that");
        }

        var playerAcceptedItemClasses = player.getPlayerClass().getAcceptedItemClasses();
        if (!playerAcceptedItemClasses.contains(toEquip.get().getItemClass())) {
            return new Result("You are not allowed to equip this item!");
        }

        player.getEquipment().removeItem(toEquip.get());

        if (player.getArmor().putItem(toEquip.get())) {
            return new Result("You have put " + toEquipName + " on");
        }

        return new Result("You can't wear that");
    }

    private Optional<IItem> getItem(final List<IItem> list, final String name) {
        return list.stream().filter(o -> o.getName().toLowerCase().equals(name)).findFirst();
    }

    @Override
    public List<GameState> getAcceptableStates() {
        return List.of(GameState.EXPLORATION);
    }
}
