package pl.uj.io.cuteanimals.plot.actions;

import java.util.List;
import java.util.Map;
import java.util.Random;
import pl.uj.io.cuteanimals.model.GameState;
import pl.uj.io.cuteanimals.model.Result;
import pl.uj.io.cuteanimals.model.interfaces.ContainerArgumentAction;
import pl.uj.io.cuteanimals.model.interfaces.ILocation;
import pl.uj.io.cuteanimals.model.interfaces.IPlayer;
import pl.uj.io.cuteanimals.model.interfaces.IResult;

/**
 * Provides methods that moves Character to the MedicalCabin in case of him falling from the bridge.
 *
 * @version %I%
 * @since 0.0.1-SNAPSHOT
 */
public class DungeonEntranceGoAction extends ContainerArgumentAction<ILocation> {
    public DungeonEntranceGoAction(Map<String, ILocation> wheres) {
        super(wheres);
    }

    @Override
    public IResult actionBody(IPlayer player, String toGoName) {
        var toGo = objects.get(toGoName);

        if (toGo == null) {
            return new Result("You want to go... where?");
        }

        Random rand = new Random();
        int result = rand.nextInt(10);
        if (result < 4) {
            toGo = player.getWorld().getLocation("medical");
        }

        player.changeLocation(toGo);
        return new Result(toGo.getDescription());
    }

    @Override
    public List<GameState> getAcceptableStates() {
        return List.of(GameState.EXPLORATION);
    }
}
