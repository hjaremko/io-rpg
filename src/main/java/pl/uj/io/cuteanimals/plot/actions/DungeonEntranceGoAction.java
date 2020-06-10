package pl.uj.io.cuteanimals.plot.actions;

import java.util.List;
import java.util.Map;
import pl.uj.io.cuteanimals.model.GameState;
import pl.uj.io.cuteanimals.model.RandomIntegerImpl;
import pl.uj.io.cuteanimals.model.Result;
import pl.uj.io.cuteanimals.model.interfaces.*;

/**
 * Provides methods that moves Character to the MedicalCabin in case of him falling from the bridge.
 *
 * @version %I%
 * @since 0.0.1-SNAPSHOT
 */
// @Component
public class DungeonEntranceGoAction extends ContainerArgumentAction<ILocation> {
    private final RandomInteger rand;

    public DungeonEntranceGoAction(Map<String, ILocation> wheres) {
        super(wheres);
        this.rand = new RandomIntegerImpl();
    }

    public DungeonEntranceGoAction(Map<String, ILocation> wheres, RandomInteger rand) {
        super(wheres);
        this.rand = rand;
    }

    @Override
    public IResult actionBody(IPlayer player, String toGoName) {
        var toGo = objects.get(toGoName);

        if (toGo == null) {
            return new Result("You want to go... where?");
        }

        if (rand.nextInt(10) < 4) {
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
