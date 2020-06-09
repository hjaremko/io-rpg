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
        if (fightState instanceof BlockState) {
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
        var expBonus = playerLevel < enemyLevel ? Math.abs(playerLevel - enemyLevel) / 2 : 0;
        var baseExp = playerAttrs.getRequiredExperience() / 6;
        var levelPenalty = playerLevel < enemyLevel ? 2 : 1;
        var gainedExperience = baseExp / levelPenalty + expBonus;

        ((PlayerAttributes) player.getAttributes()).addExperience(gainedExperience);
        player.getFightManager().setState(new ExplorationState(player));

        if (fightingWith.getName().equals("Fasilius")) {
            return new CompoundResult(
                    List.of(
                            new FightLog(
                                    "You managed to defeat Fasilius! It seems as if deadly danger has been averted. "
                                            + "You are bursting with pride. You feel incredibly exhausted. "
                                            + "You realized that the magicians are still stuck in a cell so you rush to their rescue. "
                                            + "You feel relief and incredible happiness incomparably with anything you have experienced so far. \n"
                                            + "Merlin: \"Thank you Lord for your rescue. According to the parable of the great wanderers, three alternative paths of the future await you. It is our duty to present them to you. I, Merlin present the left gate for you ...\n"
                                            + "Herschel: \"I Herschel introduce you to the middle gate ...\"\n"
                                            + "Donovan: \"I Donovan present you the right door ...\"\n"
                                            + "Merlin: \"Your whole future life depends on this choice, so choose wisely listening to the voice of your heart.\"",
                                    Color.YELLOW)));
        }

        return new CompoundResult(
                List.of(
                        new FightLog(
                                player.getFightManager().getEnemy().getName() + " is dead.",
                                Color.YELLOW),
                        new FightLog(
                                player.toString() + " gets " + gainedExperience + " experience.",
                                Color.YELLOW)));
    }

    public IResult defeat() {
        return new FightLog(player.toString() + " is dead.", Color.RED);
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
