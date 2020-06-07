package pl.uj.io.cuteanimals.model.fight;

import java.util.List;
import pl.uj.io.cuteanimals.model.*;
import pl.uj.io.cuteanimals.model.interfaces.IResult;

public class AttackState extends FightState {

    public AttackState(Player owner, Monster fightingWith, FightManager manager) {
        super(owner, fightingWith, manager);
    }

    @Override
    public IResult attack() {
        fightingWith.getAttributes().addHealth(-5);
        var mobHealthLeft = fightingWith.getAttributes().getHealth();

        if (mobHealthLeft <= 0) {
            owner.setGameState(GameState.EXPLORATION);
            ((PlayerAttributes) owner.getAttributes()).addExperience(10);
            manager.setState(new ExplorationState(owner, null, manager));

            return new FightLog(
                    fightingWith.getName()
                            + " is dead.\n"
                            + owner.toString()
                            + " gets "
                            + 10
                            + " experience.",
                    Color.YELLOW);
        }

        var playerAttackResult =
                new FightLog(
                        owner.toString()
                                + " attacks "
                                + fightingWith.getName()
                                + " for "
                                + 5
                                + " damage. "
                                + fightingWith.getName()
                                + " has "
                                + mobHealthLeft
                                + " HP left.",
                        Color.GREEN);
        var contrAttackResult = contrAttack();
        return new CompoundResult(List.of(playerAttackResult, contrAttackResult));
    }

    @Override
    public IResult contrAttack() {
        var took = owner.getDamage(10);
        var playerHealthLeft = owner.getAttributes().getHealth();

        return new FightLog(
                fightingWith.getName()
                        + " attacks "
                        + owner.toString()
                        + " for "
                        + took
                        + " damage. "
                        + owner.toString()
                        + " has "
                        + playerHealthLeft
                        + " HP left.",
                Color.RED);
    }
}
