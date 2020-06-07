package pl.uj.io.cuteanimals.model.fight;

import java.util.List;
import pl.uj.io.cuteanimals.model.*;
import pl.uj.io.cuteanimals.model.interfaces.IPlayer;
import pl.uj.io.cuteanimals.model.interfaces.IResult;

public class AttackState extends FightState {

    public AttackState(IPlayer owner) {
        super(owner);
    }

    @Override
    public IResult attack() {
        var damageDone = ((PlayerAttributes) player.getAttributes()).getDamage();
        player.getFightManager().getEnemy().getAttributes().addHealth(-damageDone);
        var mobHealthLeft = player.getFightManager().getEnemy().getAttributes().getHealth();

        if (mobHealthLeft <= 0) {
            return player.getFightManager().endBattle();
        }

        var playerAttackResult =
                new FightLog(
                        player.toString()
                                + " attacks "
                                + player.getFightManager().getEnemy().getName()
                                + " for "
                                + damageDone
                                + " damage. "
                                + player.getFightManager().getEnemy().getName()
                                + " has "
                                + mobHealthLeft
                                + " HP left.",
                        Color.GREEN);
        var contrAttackResult = contrAttack();
        return new CompoundResult(List.of(playerAttackResult, contrAttackResult));
    }

    @Override
    public IResult contrAttack() {
        var enemyStats = player.getFightManager().getEnemy().getAttributes();
        int took =
                player.takeDamage(
                        (int)
                                (enemyStats.getAttack()
                                        + Math.round((Math.random() * enemyStats.getLevel() * 3))));
        var playerHealthLeft = player.getAttributes().getHealth();

        return new FightLog(
                player.getFightManager().getEnemy().getName()
                        + " attacks "
                        + player.toString()
                        + " for "
                        + took
                        + " damage. "
                        + player.toString()
                        + " has "
                        + playerHealthLeft
                        + " HP left.",
                Color.RED);
    }
}
