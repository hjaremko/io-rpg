package pl.uj.io.cuteanimals.action.ability;

import java.util.List;
import pl.uj.io.cuteanimals.model.*;
import pl.uj.io.cuteanimals.model.fight.*;
import pl.uj.io.cuteanimals.model.interfaces.ArgumentlessAction;
import pl.uj.io.cuteanimals.model.interfaces.IAbility;
import pl.uj.io.cuteanimals.model.interfaces.IPlayer;
import pl.uj.io.cuteanimals.model.interfaces.IResult;

public class DoubleDown extends ArgumentlessAction implements IFightState, IAbility {
    private IPlayer player;
    private IFightState lastState;
    private static final int manaCost = 50;

    @Override
    protected IResult actionBody(IPlayer player) {
        if (player.getAttributes().getMana() < manaCost) {
            return new FightLog(
                    "Not enough energy! You need at least "
                            + manaCost
                            + " energy to use this ability.",
                    Color.YELLOW);
        }

        if (player.getFightManager().getState() instanceof IAbility) {
            return new FightLog("You need to unleash your rage now.", Color.YELLOW);
        }

        this.player = player;
        this.lastState = player.getFightManager().getState();
        this.player.getFightManager().setState(this);

        return new FightLog(player.toString() + " is getting angry...", Color.YELLOW);
    }

    @Override
    public List<GameState> getAcceptableStates() {
        return List.of(GameState.FIGHT);
    }

    @Override
    public IResult attack() {
        player.getAttributes().addMana(-manaCost);
        var damageDone = ((PlayerAttributes) player.getAttributes()).getDamage();
        var firstHitDmg = (int) (damageDone * 1.25);
        var secondHitDmg = (int) (damageDone * 1.70);
        var thirdHitDmg = damageDone * 2;

        player.getFightManager()
                .getEnemy()
                .getAttributes()
                .addHealth(-(firstHitDmg + secondHitDmg + thirdHitDmg));
        var mobHealthLeft = player.getFightManager().getEnemy().getAttributes().getHealth();

        if (mobHealthLeft <= 0) {
            return player.getFightManager().endBattle();
        }

        var playerAttackResult =
                new FightLog(
                        player.toString()
                                + " furiously attacks "
                                + player.getFightManager().getEnemy().getName()
                                + " for "
                                + firstHitDmg
                                + "+"
                                + secondHitDmg
                                + "+"
                                + thirdHitDmg
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
        return "Attack your enemy three consecutive times. Respectively: x1.25 dmg, x1.70 dmg, x2 dmg. Costs "
                + manaCost
                + " energy.";
    }
}
