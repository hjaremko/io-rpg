package pl.uj.io.cuteanimals.model.action;

import java.util.ArrayList;
import java.util.List;
import pl.uj.io.cuteanimals.model.*;
import pl.uj.io.cuteanimals.model.interfaces.*;

/**
 * Prints Location's "look around" message.
 *
 * @version %I%
 * @since 0.0.1-SNAPSHOT
 */
public class InvestigateAction implements IAction {
    private final String infoMessage;

    public InvestigateAction(String infoMessage) {
        this.infoMessage = infoMessage;
    }

    // TODO: argumentless actions
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
            return new Result("This isn't the time for that.");
        }

        return new Result(infoMessage);
    }

    @Override
    public List<GameState> getAcceptableStates() {
        return List.of(GameState.EXPLORATION);
    }
}
