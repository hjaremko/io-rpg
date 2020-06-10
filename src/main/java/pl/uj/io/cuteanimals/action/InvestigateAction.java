package pl.uj.io.cuteanimals.action;

import java.util.List;
import pl.uj.io.cuteanimals.model.GameState;
import pl.uj.io.cuteanimals.model.Result;
import pl.uj.io.cuteanimals.model.interfaces.ArgumentlessAction;
import pl.uj.io.cuteanimals.model.interfaces.IPlayer;
import pl.uj.io.cuteanimals.model.interfaces.IResult;

/**
 * Prints Location's "look around" message.
 *
 * @version %I%
 * @since 0.0.1-SNAPSHOT
 */
public class InvestigateAction extends ArgumentlessAction {
    private final String infoMessage;

    public InvestigateAction(String infoMessage) {
        super();
        this.infoMessage = infoMessage;
    }

    @Override
    public IResult actionBody(IPlayer player) {
        return new Result(infoMessage);
    }

    @Override
    public List<GameState> getAcceptableStates() {
        return List.of(GameState.EXPLORATION);
    }
}
