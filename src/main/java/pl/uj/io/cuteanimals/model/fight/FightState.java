package pl.uj.io.cuteanimals.model.fight;

import pl.uj.io.cuteanimals.model.Monster;
import pl.uj.io.cuteanimals.model.Player;
import pl.uj.io.cuteanimals.model.interfaces.IResult;

public abstract class FightState {
    protected final Player owner;
    protected final Monster fightingWith;
    protected final FightManager manager;

    public FightState(Player owner, Monster fightingWith, FightManager manager) {
        this.owner = owner;
        this.fightingWith = fightingWith;
        this.manager = manager;
    }

    abstract IResult attack();

    abstract IResult contrAttack();
}
