package pl.uj.io.cuteanimals.model.action;

import pl.uj.io.cuteanimals.model.GameState;
import pl.uj.io.cuteanimals.model.NPC;
import pl.uj.io.cuteanimals.model.Result;
import pl.uj.io.cuteanimals.model.interfaces.IAction;
import pl.uj.io.cuteanimals.model.interfaces.ICharacter;
import pl.uj.io.cuteanimals.model.interfaces.ILocation;
import pl.uj.io.cuteanimals.model.interfaces.IResult;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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
            return new Result("You want to talk... who?");
        }
        if (!getAcceptableStates().contains(character.getCurrentGameState())) {
            return new Result("This action cannot be executed now");
        }
        var npc = location.getNPCs()
                .stream()
                .filter(x -> x.getName().equals(args.get(0)))
                .collect(Collectors.toList());
        return npc.size() >= 1
                ? new Result(npc.get(0).getQuote())
                : new Result("This action cannot be executed now");
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
        return List.of(GameState.EXPLORATION);
    }
}
