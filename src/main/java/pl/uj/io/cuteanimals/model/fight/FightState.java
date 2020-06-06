package pl.uj.io.cuteanimals.model.fight;

import pl.uj.io.cuteanimals.model.Monster;
import pl.uj.io.cuteanimals.model.Player;

public abstract class FightState {
    protected final Player owner;
    protected final Monster fightingWith;
    protected final pl.uj.io.cuteanimals.model.fight.FightManager manager;

    public FightState(
            Player owner,
            Monster fightingWith,
            pl.uj.io.cuteanimals.model.fight.FightManager manager) {
        this.owner = owner;
        this.fightingWith = fightingWith;
        this.manager = manager;
    }

    abstract String attack();

    abstract String contrAttack();
}
