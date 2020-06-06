package pl.uj.io.cuteanimals.action;

import java.util.List;
import java.util.Map;
import pl.uj.io.cuteanimals.model.GameState;
import pl.uj.io.cuteanimals.model.Monster;
import pl.uj.io.cuteanimals.model.Result;
import pl.uj.io.cuteanimals.model.interfaces.*;

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

        return new Result(player.getFightManager().beginFight(toFight));
    }

    @Override
    public List<GameState> getAcceptableStates() {
        return List.of(GameState.EXPLORATION);
    }
}
