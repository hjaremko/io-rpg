package pl.uj.io.cuteanimals.action.entrance;

import java.util.List;
import pl.uj.io.cuteanimals.model.Color;
import pl.uj.io.cuteanimals.model.GameState;
import pl.uj.io.cuteanimals.model.Result;
import pl.uj.io.cuteanimals.model.interfaces.IAction;
import pl.uj.io.cuteanimals.model.interfaces.IPlayer;
import pl.uj.io.cuteanimals.model.interfaces.IResult;

public class EntranceRemoveHealthAction implements IAction {
    private final int healthLoss;

    public EntranceRemoveHealthAction(int healthLoss) {
        this.healthLoss = healthLoss;
    }

    @Override
    public IResult execute(IPlayer player) {
        player.getAttributes().addHealth(-healthLoss);
        return new Result("(You lose " + healthLoss + " health points).", Color.RED);
    }

    @Override
    public List<String> getArgs() {
        return null;
    }

    @Override
    public void setArgs(List<String> args) {
        // Ignored
    }

    @Override
    public List<GameState> getAcceptableStates() {
        return null;
    }
}
