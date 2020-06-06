package pl.uj.io.cuteanimals.plot.actions;

import java.util.List;
import pl.uj.io.cuteanimals.model.GameState;
import pl.uj.io.cuteanimals.model.Result;
import pl.uj.io.cuteanimals.model.interfaces.*;

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
