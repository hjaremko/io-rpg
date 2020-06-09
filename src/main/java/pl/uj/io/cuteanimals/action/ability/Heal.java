package pl.uj.io.cuteanimals.action.ability;

import java.util.List;
import pl.uj.io.cuteanimals.model.*;
import pl.uj.io.cuteanimals.model.fight.*;
import pl.uj.io.cuteanimals.model.interfaces.ArgumentlessAction;
import pl.uj.io.cuteanimals.model.interfaces.IAbility;
import pl.uj.io.cuteanimals.model.interfaces.IPlayer;
import pl.uj.io.cuteanimals.model.interfaces.IResult;

public class Heal extends ArgumentlessAction implements IFightState, IAbility {
    private IPlayer player;
    private IFightState lastState;
    private static final int manaCost = 60;
    private static final int hpBonus = 10;

    @Override
    protected IResult actionBody(IPlayer player) {
        if (player.getAttributes().getMana() < manaCost) {
            return new FightLog(
                    "Not enough mana! You need at least " + manaCost + " mana to use this ability.",
                    Color.YELLOW);
        }

        if (player.getFightManager().getState() instanceof IAbility) {
            return new FightLog("You are already casting a spell.", Color.YELLOW);
        }

        this.player = player;
        this.lastState = player.getFightManager().getState();
        this.player.getFightManager().setState(this);

        return new FightLog(player.toString() + " is preparing to heal himself...", Color.YELLOW);
    }

    @Override
    public List<GameState> getAcceptableStates() {
        return List.of(GameState.FIGHT);
    }

    @Override
    public IResult attack() {
        player.getAttributes().addMana(-manaCost);
        player.getAttributes().addHealth(hpBonus);

        var playerAttackResult =
                new FightLog(
                        player.toString()
                                + " heals himself for "
                                + hpBonus
                                + " HP. "
                                + player.toString()
                                + " has "
                                + player.getAttributes().getHealth()
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
        return "Heal yourself. +" + hpBonus + " HP. Costs " + manaCost + " mana.";
    }
}
