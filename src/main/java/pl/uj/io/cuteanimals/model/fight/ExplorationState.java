package pl.uj.io.cuteanimals.model.fight;

import pl.uj.io.cuteanimals.model.Monster;
import pl.uj.io.cuteanimals.model.Player;
import pl.uj.io.cuteanimals.model.Result;
import pl.uj.io.cuteanimals.model.interfaces.IResult;

public class ExplorationState extends FightState {
    public ExplorationState(Player owner, Monster fightingWith, FightManager manager) {
        super(owner, fightingWith, manager);
    }

    @Override
    IResult attack() {
        return new Result("");
    }

    @Override
    IResult contrAttack() {
        return new Result("");
    }
}
