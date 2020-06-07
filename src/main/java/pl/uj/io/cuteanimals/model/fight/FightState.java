package pl.uj.io.cuteanimals.model.fight;

import pl.uj.io.cuteanimals.model.interfaces.IPlayer;

public abstract class FightState implements IFightState {
    protected final IPlayer player;

    public FightState(IPlayer player) {
        this.player = player;
    }
}
