package pl.uj.io.cuteanimals.action;

import java.util.List;
import java.util.stream.Collectors;
import pl.uj.io.cuteanimals.model.GameState;
import pl.uj.io.cuteanimals.model.Result;
import pl.uj.io.cuteanimals.model.interfaces.ArgumentlessAction;
import pl.uj.io.cuteanimals.model.interfaces.IAbility;
import pl.uj.io.cuteanimals.model.interfaces.IPlayer;
import pl.uj.io.cuteanimals.model.interfaces.IResult;

public class ShowAbilities extends ArgumentlessAction {
    @Override
    protected IResult actionBody(IPlayer player) {
        return new Result(
                player.getAbilities()
                        .entrySet()
                        .stream()
                        .map(a -> a.getKey() + ": " + ((IAbility) a.getValue()).getDescription())
                        .collect(Collectors.joining("\n")));
    }

    @Override
    public List<GameState> getAcceptableStates() {
        return List.of(GameState.EXPLORATION, GameState.FIGHT);
    }
}
