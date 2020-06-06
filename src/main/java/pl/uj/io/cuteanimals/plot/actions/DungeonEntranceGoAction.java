package pl.uj.io.cuteanimals.plot.actions;

import java.util.List;
import java.util.Map;
import java.util.Random;
import pl.uj.io.cuteanimals.model.*;
import pl.uj.io.cuteanimals.model.interfaces.*;

public class DungeonEntranceGoAction extends ArgumentAction {
    private final Map<String, ILocation> locations;

    public DungeonEntranceGoAction(Map<String, ILocation> wheres) {
        super();
        locations = wheres;
    }

    @Override
    public IResult execute(IPlayer player) {
        if (!getAcceptableStates().contains(player.getCurrentGameState())) {
            return new Result("This action cannot be executed now");
        }

        var joined = String.join(" ", getArgs());
        var toGo = locations.get(joined);
        getArgs().clear();

        if (toGo == null) {
            return new Result("You want to go... where?");
        }

        Random rand = new Random();
        int result = rand.nextInt(10);
        if (result < 4) {
            toGo = WorldMap.getInstance().getLocation("medical");
        }

        player.changeLocation(toGo);
        return new Result(toGo.getDescription());
    }

    @Override
    public List<GameState> getAcceptableStates() {
        return List.of(GameState.EXPLORATION);
    }
}
