package pl.uj.io.cuteanimals.action.ability;

import java.util.List;
import pl.uj.io.cuteanimals.model.*;
import pl.uj.io.cuteanimals.model.fight.*;
import pl.uj.io.cuteanimals.model.interfaces.ArgumentlessAction;
import pl.uj.io.cuteanimals.model.interfaces.IAbility;
import pl.uj.io.cuteanimals.model.interfaces.IPlayer;
import pl.uj.io.cuteanimals.model.interfaces.IResult;

public class Bullseye extends ArgumentlessAction implements IFightState, IAbility {
    private IPlayer player;
    private IFightState lastState;
    private static final int manaCost = 33;

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
            return new FightLog("You are not that good at multitasking.", Color.YELLOW);
        }

        this.player = player;
        this.lastState = player.getFightManager().getState();
        this.player.getFightManager().setState(this);

        return new FightLog(player.toString() + " draws bow...", Color.YELLOW);
    }

    @Override
    public List<GameState> getAcceptableStates() {
        return List.of(GameState.FIGHT);
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
                                + " eyes for "
                                + damageDone
                                + " damage. "
                                + player.getFightManager().getEnemy().getName()
                                + " has "
                                + mobHealthLeft
                                + " HP left.",
                        Color.GREEN);

        player.getFightManager().setState(lastState);
        return new CompoundResult(List.of(playerAttackResult, contrAttack()));
    }

    @Override
    public IResult contrAttack() {
        return new FightLog(
                player.getFightManager().getEnemy().getName() + " misses. Loser.", Color.RED);
    }

    @Override
    public String getDescription() {
        return "Blind your enemy by aiming at its eyes. Causes your enemy to miss its next attack. Costs "
                + manaCost
                + " energy.";
    }
}
