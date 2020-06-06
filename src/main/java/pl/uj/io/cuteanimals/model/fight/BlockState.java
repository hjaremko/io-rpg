package pl.uj.io.cuteanimals.model.fight;

import pl.uj.io.cuteanimals.model.Monster;
import pl.uj.io.cuteanimals.model.Player;

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
    public String attack() {
        return "\u001b[33m+ " + owner.toString() + " is blocking.\u001b[0m\n" + contrAttack();
    }

    @Override
    public String contrAttack() {
        var result = super.contrAttack();
        uses--;

        if (uses == 0) {
            owner.getAttributes().addDefence(-defenceIncrease);
            manager.setState(new AttackState(owner, fightingWith, manager));
            result += "\nBlock wears off.";
        }

        return result;
    }
}
