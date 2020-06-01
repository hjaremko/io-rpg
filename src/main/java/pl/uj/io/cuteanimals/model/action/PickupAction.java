package pl.uj.io.cuteanimals.model.action;

import java.util.ArrayList;
import java.util.List;
import pl.uj.io.cuteanimals.model.Result;
import pl.uj.io.cuteanimals.model.interfaces.IAction;
import pl.uj.io.cuteanimals.model.interfaces.ICharacter;
import pl.uj.io.cuteanimals.model.interfaces.IItem;
import pl.uj.io.cuteanimals.model.interfaces.IResult;

public class PickupAction implements IAction {
    IItem toPickup;
    private List<String> args;
    private final List<String> names;

    public PickupAction(IItem toPickup, List<String> itemNames) {
        this.toPickup = toPickup;
        this.args = new ArrayList<>();
        this.names = itemNames;
    }

    @Override
    public IResult execute(ICharacter character) {
        if (toPickup == null) {
            return new Result("Nothing here");
        }

        var joined = String.join(" ", args);
        args.clear();

        if (!names.contains(joined)) {
            return new Result("What do you want to pick up?");
        }

        character.getEquipment().putItem(toPickup);
        var itemName = toPickup.getName();
        toPickup = null;
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
}
