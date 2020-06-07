package pl.uj.io.cuteanimals.model.fight;

import java.util.List;
import pl.uj.io.cuteanimals.model.*;
import pl.uj.io.cuteanimals.model.interfaces.IResult;

public class FightManager {
    private final Player player;
    private Monster fightingWith;
    private IFightState fightState;

    public FightManager(Player player) {
        this.player = player;
        this.fightState = new ExplorationState(this.player);
    }

    public String beginFight(Monster monster) {
        this.fightingWith = monster;
        this.fightState = new AttackState(player);
        player.setGameState(GameState.FIGHT);

        return "Fight begins: "
                + player.toString()
                + " ("
                + player.getAttributes().getLevel()
                + ") vs. "
                + monster.getName()
                + " ("
                + monster.getAttributes().getLevel()
                + ")";
    }

    public IResult attack() {
        return fightState.attack();
    }

    public IResult contrAttack() {
        return fightState.contrAttack();
    }

    public IResult block() {
        if (fightState.getClass().equals(BlockState.class)) {
            return new FightLog("You are already blocking.", Color.YELLOW);
        }

        fightState = new BlockState(player);

        var blockMessage = player.toString() + " is preparing to block incoming attacks.";
        return new FightLog(blockMessage, Color.YELLOW);
    }

    public IResult endBattle() {
        player.setGameState(GameState.EXPLORATION);

        var playerAttrs = (PlayerAttributes) player.getAttributes();
        var playerLevel = playerAttrs.getLevel();
        var enemyLevel = fightingWith.getAttributes().getLevel();
        var gainedExperience =
                (int)
                        Math.round(
                                ((playerAttrs.getRequiredExperience() / 6.0)
                                        * (playerLevel < enemyLevel
                                                ? 0.5
                                                : 1 + Math.abs(playerLevel - enemyLevel) * 0.5)));

        ((PlayerAttributes) player.getAttributes()).addExperience(gainedExperience);
        player.getFightManager().setState(new ExplorationState(player));

        return new CompoundResult(
                List.of(
                        new FightLog(
                                player.getFightManager().getEnemy().getName() + " is dead.",
                                Color.YELLOW),
                        new FightLog(
                                player.toString() + " gets " + gainedExperience + " experience.",
                                Color.YELLOW)));
    }

    public Monster getEnemy() {
        return fightingWith;
    }

    public IFightState getState() {
        return fightState;
    }

    public void setState(IFightState fightState) {
        this.fightState = fightState;
    }
}
