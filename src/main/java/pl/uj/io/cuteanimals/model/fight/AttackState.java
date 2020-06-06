package pl.uj.io.cuteanimals.model.fight;

import pl.uj.io.cuteanimals.model.GameState;
import pl.uj.io.cuteanimals.model.Monster;
import pl.uj.io.cuteanimals.model.Player;
import pl.uj.io.cuteanimals.model.PlayerAttributes;

public class AttackState extends FightState {

    public AttackState(Player owner, Monster fightingWith, FightManager manager) {
        super(owner, fightingWith, manager);
    }

    @Override
    public String attack() {
        fightingWith.getAttributes().addHealth(-5);
        var mobHealthLeft = fightingWith.getAttributes().getHealth();

        if (mobHealthLeft <= 0) {
            owner.setGameState(GameState.EXPLORATION);
            ((PlayerAttributes) owner.getAttributes()).addExperience(10);
            manager.setState(new ExplorationState(owner, null, manager));

            return "\u001b[33m+ "
                    + fightingWith.getName()
                    + " is dead.\n* "
                    + owner.toString()
                    + " gets "
                    + 10
                    + " experience.\u001b[0m";
        }

        var playerAttackResult =
                "\u001b[32m+ "
                        + owner.toString()
                        + " attacks "
                        + fightingWith.getName()
                        + " for "
                        + 5
                        + " damage.\u001b[0m\n* "
                        + fightingWith.getName()
                        + " has "
                        + mobHealthLeft
                        + " HP left.";
        var contrAttackResult = contrAttack();

        return playerAttackResult + "\n" + contrAttackResult;
    }

    @Override
    public String contrAttack() {
        var took = owner.getDamage(10);
        var playerHealthLeft = owner.getAttributes().getHealth();

        return "\u001b[31m- "
                + fightingWith.getName()
                + " attacks "
                + owner.toString()
                + " for "
                + took
                + " damage.\u001b[0m\n* "
                + owner.toString()
                + " has "
                + playerHealthLeft
                + " HP left.";
    }
}
