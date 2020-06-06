package pl.uj.io.cuteanimals.model.action;

import java.util.ArrayList;
import java.util.List;
import pl.uj.io.cuteanimals.model.GameState;
import pl.uj.io.cuteanimals.model.Result;
import pl.uj.io.cuteanimals.model.interfaces.IAction;
import pl.uj.io.cuteanimals.model.interfaces.ICharacter;
import pl.uj.io.cuteanimals.model.interfaces.IItem;
import pl.uj.io.cuteanimals.model.interfaces.IResult;

public class DungeonInvestigateAction implements IAction {
    private final String infoMessage;

    public DungeonInvestigateAction(String infoMessage) {
        this.infoMessage = infoMessage;
    }

    @Override
    public List<String> getArgs() {
        return new ArrayList<>();
    }

    @Override
    public void setArgs(List<String> unused) {
        // Investigate is argumentless.
    }

    @Override
    public IResult execute(ICharacter character) {
        if (!getAcceptableStates().contains(character.getCurrentGameState())) {
            return new Result("This action cannot be executed now");
        }

        for (IItem item : character.getArmor().getItems()) {
            if (item.getName().equals("torch")) {
                return new Result(infoMessage);
            }
        }

        return new Result("You can see nothing but the darkness.");
    }

    @Override
    public List<GameState> getAcceptableStates() {
        return List.of(GameState.EXPLORATION);
    }
}
