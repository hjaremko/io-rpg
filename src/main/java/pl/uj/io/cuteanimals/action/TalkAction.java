package pl.uj.io.cuteanimals.action;

import java.util.List;
import java.util.stream.Collectors;
import pl.uj.io.cuteanimals.model.GameState;
import pl.uj.io.cuteanimals.model.Result;
import pl.uj.io.cuteanimals.model.interfaces.*;

/**
 * Provides methods to engage a conversation with NPC's.
 *
 * @version %I%
 * @since 0.0.1-SNAPSHOT
 */
public class TalkAction extends ArgumentAction {
    private final ILocation location;

    public TalkAction(ILocation location) {
        super();
        this.location = location;
    }

    @Override
    public IResult actionBody(IPlayer player, String npcName) {
        var npc =
                location.getNPCs()
                        .stream()
                        .filter(x -> x.getName().toLowerCase().equals(npcName))
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
