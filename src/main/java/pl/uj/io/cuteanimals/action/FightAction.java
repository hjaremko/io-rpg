package pl.uj.io.cuteanimals.action;

import java.util.List;
import java.util.Map;
import pl.uj.io.cuteanimals.model.Color;
import pl.uj.io.cuteanimals.model.GameState;
import pl.uj.io.cuteanimals.model.Monster;
import pl.uj.io.cuteanimals.model.Result;
import pl.uj.io.cuteanimals.model.fight.FightLog;
import pl.uj.io.cuteanimals.model.interfaces.ContainerArgumentAction;
import pl.uj.io.cuteanimals.model.interfaces.IPlayer;
import pl.uj.io.cuteanimals.model.interfaces.IResult;

public class FightAction extends ContainerArgumentAction<Monster> {
    public FightAction(Map<String, Monster> monsters) {
        super(monsters);
    }

    @Override
    public IResult actionBody(IPlayer player, String toFightName) {
        var toFight = objects.get(toFightName);

        if (toFight == null) {
            return new Result("You want to fight... who?");
        }

        if (toFight.getAttributes().getHealth() <= 0) {
            return new Result(toFight.getName() + " is already dead.");
        }

        return new FightLog(player.getFightManager().beginFight(toFight), Color.YELLOW);
    }

    @Override
    public List<GameState> getAcceptableStates() {
        return List.of(GameState.EXPLORATION);
    }
}
