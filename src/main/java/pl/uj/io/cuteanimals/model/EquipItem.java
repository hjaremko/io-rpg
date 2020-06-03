package pl.uj.io.cuteanimals.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import pl.uj.io.cuteanimals.model.interfaces.IAction;
import pl.uj.io.cuteanimals.model.interfaces.ICharacter;
import pl.uj.io.cuteanimals.model.interfaces.IItem;
import pl.uj.io.cuteanimals.model.interfaces.IResult;

public class EquipItem implements IAction {
    private List<String> args;

    public EquipItem() {
        this.args = new ArrayList<>();
    }

    @Override
    public IResult execute(ICharacter character) {
        if (!getAcceptableStates().contains(character.getCurrentGameState())) {
            return new Result("This action cannot be executed now");
        }

        var joined = String.join(" ", args);
        args.clear();

        var toEquip = getItem(character.getEquipment().getItems(), joined);

        if (toEquip.isEmpty()) {
            return new Result("You don't have that");
        }

        var itemName = toEquip.get().getName();
        character.getEquipment().removeItem(toEquip.get());

        if (character.getArmor().putItem(toEquip.get())) {
            return new Result("You have put " + itemName + " on");
        }

        return new Result("You can't wear that");
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
