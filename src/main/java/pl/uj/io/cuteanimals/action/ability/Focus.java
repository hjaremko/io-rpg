package pl.uj.io.cuteanimals.action.ability;

import java.util.List;
import pl.uj.io.cuteanimals.model.*;
import pl.uj.io.cuteanimals.model.fight.*;
import pl.uj.io.cuteanimals.model.interfaces.ArgumentlessAction;
import pl.uj.io.cuteanimals.model.interfaces.IPlayer;
import pl.uj.io.cuteanimals.model.interfaces.IResult;

public class Focus extends ArgumentlessAction implements IFightState, IAbility {
    private IPlayer player;
    private IFightState lastState;

    @Override
    protected IResult actionBody(IPlayer player) {
        if (((PlayerAttributes) player.getAttributes()).getMana() < 20) {
            return new FightLog(
                    "Not enough mana! You need at least 20 mana to use this ability.",
                    Color.YELLOW);
        }

        this.player = player;
        this.lastState = player.getFightManager().getState();
        this.player.getFightManager().setState(this);

        return new FightLog(player.toString() + " is focusing...", Color.YELLOW);
    }

    @Override
    public List<GameState> getAcceptableStates() {
        return List.of(GameState.FIGHT);
    }

    @Override
    public IResult attack() {
        ((PlayerAttributes) player.getAttributes()).addMana(-20);
        var damageDone = ((PlayerAttributes) player.getAttributes()).getDamage();
        var additionalDamage = 2 * player.getAttributes().getAttack();
        player.getFightManager()
                .getEnemy()
                .getAttributes()
                .addHealth(damageDone - additionalDamage);
        var mobHealthLeft = player.getFightManager().getEnemy().getAttributes().getHealth();

        if (mobHealthLeft <= 0) {
            return player.getFightManager().endBattle();
        }

        var playerAttackResult =
                new FightLog(
                        player.toString()
                                + " attacks "
                                + player.getFightManager().getEnemy().getName()
                                + " vital points for "
                                + damageDone
                                + "+"
                                + additionalDamage
                                + " damage. "
                                + player.getFightManager().getEnemy().getName()
                                + " has "
                                + mobHealthLeft
                                + " HP left.",
                        Color.GREEN);

        player.getFightManager().setState(lastState);
        var contrAttackResult = contrAttack();

        return new CompoundResult(List.of(playerAttackResult, contrAttackResult));
    }

    @Override
    public IResult contrAttack() {
        return player.getFightManager().contrAttack();
    }

    @Override
    public String getDescription() {
        return "Focus your attack on enemy's vital points. +2 * Attack damage. Costs 20 mana.";
    }
}
