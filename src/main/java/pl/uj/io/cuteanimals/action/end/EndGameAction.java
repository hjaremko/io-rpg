package pl.uj.io.cuteanimals.action.end;

import pl.uj.io.cuteanimals.model.GameState;
import pl.uj.io.cuteanimals.model.Result;
import pl.uj.io.cuteanimals.model.interfaces.IAction;
import pl.uj.io.cuteanimals.model.interfaces.IPlayer;
import pl.uj.io.cuteanimals.model.interfaces.IResult;

import java.util.List;

public class EndGameAction implements IAction {

    @Override
    public IResult execute(IPlayer player) {
        player.getAttributes().addHealth(-player.getAttributes().getHealth());
        return new Result("\n\nYou ended the game. Congratulations! Thank you for playing :)");
    }

    @Override
    public List<String> getArgs() {
        return null;
    }

    @Override
    public void setArgs(List<String> args) {

    }

    @Override
    public List<GameState> getAcceptableStates() {
        return null;
    }
}
