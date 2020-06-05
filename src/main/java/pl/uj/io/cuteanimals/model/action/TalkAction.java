package pl.uj.io.cuteanimals.model.action;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import pl.uj.io.cuteanimals.model.GameState;
import pl.uj.io.cuteanimals.model.Result;
import pl.uj.io.cuteanimals.model.interfaces.IAction;
import pl.uj.io.cuteanimals.model.interfaces.ICharacter;
import pl.uj.io.cuteanimals.model.interfaces.ILocation;
import pl.uj.io.cuteanimals.model.interfaces.IResult;

public class TalkAction implements IAction {

    private List<String> args;

    private ILocation location;

    public TalkAction() {
        this.args = new ArrayList<>();
    }

    public TalkAction(List<String> args, ILocation location) {
        this.args = args;
        this.location = location;
    }

    @Override
    public IResult execute(ICharacter character) {
        if (args.size() == 0) {
            return new Result("Who do you want to talk to?");
        }

        if (!getAcceptableStates().contains(character.getCurrentGameState())) {
            return new Result("This isn't the time for that.");
        }

        var npc =
                location.getNPCs()
                        .stream()
                        .filter(x -> x.getName().equals(args.get(0)))
                        .collect(Collectors.toList());
        args.clear();

        return npc.size() >= 1
                ? new Result(npc.get(0).getQuote())
                : new Result("There is nobody named like that.");
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
    public List<GameState> getAcceptableStates() {
        return List.of(GameState.EXPLORATION, GameState.FIGHT);
    }
}
