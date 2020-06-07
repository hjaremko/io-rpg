package pl.uj.io.cuteanimals.model.fight;

import pl.uj.io.cuteanimals.model.interfaces.IPlayer;
import pl.uj.io.cuteanimals.model.interfaces.IResult;

public class ExplorationState extends FightState {
    public ExplorationState(IPlayer owner) {
        super(owner);
    }

    @Override
    public IResult attack() {
        return null;
    }

    @Override
    public IResult contrAttack() {
        return null;
    }
}
