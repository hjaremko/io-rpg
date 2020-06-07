package pl.uj.io.cuteanimals.action;

import java.util.List;
import java.util.stream.Collectors;
import pl.uj.io.cuteanimals.model.GameState;
import pl.uj.io.cuteanimals.model.Result;
import pl.uj.io.cuteanimals.model.interfaces.*;

public class TalkAction extends ArgumentAction {
    private ILocation location;

    public TalkAction() {
        super();
    }

    public TalkAction(List<String> args, ILocation location) {
        super();
        setArgs(args);
        this.location = location;
    }

    @Override
    public IResult execute(ICharacter character) {
        if (getArgs().isEmpty()) {
            return new Result("Who do you want to talk to?");
        }

        if (!getAcceptableStates().contains(character.getCurrentGameState())) {
            return new Result("This isn't the time for that.");
        }

        var npc =
                location.getNPCs()
                        .stream()
                        .filter(x -> x.getName().toLowerCase().equals(getArgs().get(0)))
                        .collect(Collectors.toList());
        getArgs().clear();

        return npc.size() >= 1
                ? new Result(npc.get(0).getQuote())
                : new Result("There is nobody named like that.");
    }

    @Override
    public List<GameState> getAcceptableStates() {
        return List.of(GameState.EXPLORATION, GameState.FIGHT);
    }
}
