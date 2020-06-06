package pl.uj.io.cuteanimals.model.fight;

import pl.uj.io.cuteanimals.model.*;

public class FightManager {
    private final Player owner;
    private Monster fightingWith;
    private FightState fightState;

    public FightManager(Player player) {
        this.owner = player;
        this.fightState = new ExplorationState(owner, null, this);
    }

    public String beginFight(Monster monster) {
        this.fightingWith = monster;
        this.fightState = new AttackState(owner, fightingWith, this);
        owner.setGameState(GameState.FIGHT);

        return "Fight begins: "
                + owner.toString()
                + " ("
                + owner.getAttributes().getLevel()
                + ") vs. "
                + monster.getName()
                + " ("
                + monster.getAttributes().getLevel()
                + ")";
    }

    public Result attack() {
        return new Result(fightState.attack());
    }

    public String contrAttack() {
        return fightState.contrAttack();
    }

    public Result block() {
        if (fightState.getClass().equals(BlockState.class)) {
            return new Result("You are already blocking.");
        }

        fightState = new BlockState(owner, fightingWith, this);

        var blockMessage = owner.toString() + " is preparing to block incoming attacks.";
        return new Result(blockMessage);
    }

    public FightState getState() {
        return fightState;
    }

    public void setState(FightState fightState) {
        this.fightState = fightState;
    }
}
