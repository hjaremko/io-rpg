package pl.uj.io.cuteanimals.model.fight;

import java.util.List;
import pl.uj.io.cuteanimals.model.Color;
import pl.uj.io.cuteanimals.model.CompoundResult;
import pl.uj.io.cuteanimals.model.Monster;
import pl.uj.io.cuteanimals.model.Player;
import pl.uj.io.cuteanimals.model.interfaces.IResult;

public class BlockState extends AttackState {
    private final int defenceIncrease;
    private int uses;

    public BlockState(Player owner, Monster fightingWith, FightManager manager) {
        super(owner, fightingWith, manager);
        this.uses = 2;
        this.defenceIncrease = owner.getAttributes().getDefence();
        owner.getAttributes().addDefence(defenceIncrease);
    }

    @Override
    public IResult attack() {
        return new FightLog(owner.toString() + " is blocking.\n" + contrAttack(), Color.YELLOW);
    }

    @Override
    public IResult contrAttack() {
        var result = super.contrAttack();
        uses--;

        if (uses == 0) {
            owner.getAttributes().addDefence(-defenceIncrease);
            manager.setState(new AttackState(owner, fightingWith, manager));
            return new CompoundResult(
                    List.of(result, new FightLog("Block wears off.", Color.YELLOW)));
        }

        return result;
    }
}
