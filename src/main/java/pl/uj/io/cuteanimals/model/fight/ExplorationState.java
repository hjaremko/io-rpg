package pl.uj.io.cuteanimals.model.fight;

import pl.uj.io.cuteanimals.model.Monster;
import pl.uj.io.cuteanimals.model.Player;

public class ExplorationState extends FightState {
    public ExplorationState(Player owner, Monster fightingWith, FightManager manager) {
        super(owner, fightingWith, manager);
    }

    @Override
    String attack() {
        return "";
    }

    @Override
    String contrAttack() {
        return "";
    }
}
