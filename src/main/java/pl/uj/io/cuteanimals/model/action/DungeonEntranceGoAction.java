package pl.uj.io.cuteanimals.model.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import pl.uj.io.cuteanimals.model.*;
import pl.uj.io.cuteanimals.model.interfaces.IAction;
import pl.uj.io.cuteanimals.model.interfaces.ICharacter;
import pl.uj.io.cuteanimals.model.interfaces.ILocation;
import pl.uj.io.cuteanimals.model.interfaces.IResult;

public class DungeonEntranceGoAction implements IAction {
    private final Map<String, ILocation> locations;
    private List<String> args;

    public DungeonEntranceGoAction(Map<String, ILocation> wheres) {
        locations = wheres;
        args = new ArrayList<>();
    }

    @Override
    public List<String> getArgs() {
        return args;
    }

    @Override
    public void setArgs(List<String> args) {
        this.args = args;
    }

    @Override
    public IResult execute(ICharacter character) {
        if (!getAcceptableStates().contains(character.getCurrentGameState())) {
            return new Result("This action cannot be executed now");
        }

        var joined = String.join(" ", args);
        var toGo = locations.get(joined);
        args.clear();

        if (toGo == null) {
            return new Result("You want to go... where?");
        }

        Random rand = new Random();
        int result = rand.nextInt(10);
        if (result < 4) {
            toGo = WorldMap.getInstance().getLocation("medical");
        }

        character.changeLocation(toGo);
        return new Result(toGo.getDescription());
    }

    @Override
    public List<GameState> getAcceptableStates() {
        return List.of(GameState.EXPLORATION);
    }
}
