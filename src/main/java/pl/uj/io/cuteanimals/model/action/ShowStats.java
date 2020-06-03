package pl.uj.io.cuteanimals.model.action;

import java.util.List;
import pl.uj.io.cuteanimals.model.GameState;
import pl.uj.io.cuteanimals.model.PlayerAttributes;
import pl.uj.io.cuteanimals.model.Result;
import pl.uj.io.cuteanimals.model.interfaces.IAction;
import pl.uj.io.cuteanimals.model.interfaces.ICharacter;
import pl.uj.io.cuteanimals.model.interfaces.IResult;

public class ShowStats implements IAction {
    @Override
    public IResult execute(ICharacter character) {
        if (!getAcceptableStates().contains(character.getCurrentGameState())) {
            return new Result("This action cannot be executed now");
        }

        var exp =
                "Experience: "
                        + ((PlayerAttributes) character.getAttributes()).getExperience()
                        + "/"
                        + ((PlayerAttributes) character.getAttributes()).getRequiredExperience()
                        + "\n";
        return new Result(
                exp
                        + "Health: "
                        + character.getAttributes().getHealth()
                        + "\n"
                        + "Level: "
                        + character.getAttributes().getLevel()
                        + "\n"
                        + "Attack: "
                        + character.getAttributes().getAttack()
                        + "\n"
                        + "Defence: "
                        + character.getAttributes().getDefence()
                        + "\n");
    }

    @Override
    public List<String> getArgs() {
        return null;
    }

    @Override
    public void setArgs(List<String> args) {}

    @Override
    public List<GameState> getAcceptableStates() {
        return List.of(GameState.EXPLORATION, GameState.FIGHT);
    }
}
