package pl.uj.io.cuteanimals.plot.actions;

import java.util.List;
import pl.uj.io.cuteanimals.model.GameState;
import pl.uj.io.cuteanimals.model.Result;
import pl.uj.io.cuteanimals.model.interfaces.ArgumentlessAction;
import pl.uj.io.cuteanimals.model.interfaces.IItem;
import pl.uj.io.cuteanimals.model.interfaces.IPlayer;
import pl.uj.io.cuteanimals.model.interfaces.IResult;

/**
 * Provides method that forbid Character to investigate the Dungeon without the Torch equipped.
 *
 * @version %I%
 * @since 0.0.1-SNAPSHOT
 */
public class DungeonInvestigateAction extends ArgumentlessAction {
    private final String infoMessage;

    public DungeonInvestigateAction(String infoMessage) {
        super();
        this.infoMessage = infoMessage;
    }

    @Override
    public IResult actionBody(IPlayer player) {
        if (!getAcceptableStates().contains(player.getCurrentGameState())) {
            return new Result("This action cannot be executed now");
        }

        for (IItem item : player.getArmor().getItems()) {
            if (item.getName().toLowerCase().equals("torch")) {
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
