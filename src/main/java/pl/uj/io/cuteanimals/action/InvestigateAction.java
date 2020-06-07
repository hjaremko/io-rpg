package pl.uj.io.cuteanimals.action;

import java.util.List;
import pl.uj.io.cuteanimals.model.*;
import pl.uj.io.cuteanimals.model.interfaces.*;

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
