package pl.uj.io.cuteanimals.action;

import java.util.List;
import java.util.Map;
import pl.uj.io.cuteanimals.model.GameState;
import pl.uj.io.cuteanimals.model.Result;
import pl.uj.io.cuteanimals.model.interfaces.*;

public class PickupAction extends ContainerArgumentAction<IItem> {

    public PickupAction(Map<String, IItem> items) {
        super(items);
    }

    @Override
    public IResult actionBody(IPlayer player, String toPickupName) {
        var toPickup = objects.get(toPickupName);

        if (toPickup == null) {
            return new Result("Nothing here");
        }

        if (!player.getEquipment().putItem(toPickup)) {
            return new Result("This item is too heavy!");
        }

        // TODO: after picking up gold in chamberOfWealth add money

        var itemName = toPickup.getName();
        objects.remove(toPickupName);
        return new Result("You have picked " + itemName + " up");
    }

    @Override
    public List<GameState> getAcceptableStates() {
        return List.of(GameState.EXPLORATION);
    }
}
