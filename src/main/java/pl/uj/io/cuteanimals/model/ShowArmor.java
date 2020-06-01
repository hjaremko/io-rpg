package pl.uj.io.cuteanimals.model;

import java.util.ArrayList;
import java.util.List;
import pl.uj.io.cuteanimals.model.interfaces.IAction;
import pl.uj.io.cuteanimals.model.interfaces.ICharacter;
import pl.uj.io.cuteanimals.model.interfaces.IResult;

public class ShowArmor implements IAction {
    @Override
    public IResult execute(ICharacter character) {
        return new Result(character.getArmor().showItems());
    }

    @Override
    public List<String> getArgs() {
        return new ArrayList<>();
    }

    @Override
    public void setArgs(List<String> args) {}
}
