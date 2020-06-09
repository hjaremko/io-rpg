package pl.uj.io.cuteanimals.model;

import pl.uj.io.cuteanimals.model.interfaces.IAttributes;

public class NPCAttributes implements IAttributes {
    protected int health;
    protected int attack;
    protected int level;
    protected int defence;
    protected int mana;

    public NPCAttributes(int health, int attack, int level, int defence, int mana) {
        this.health = health;
        this.attack = attack;
        this.level = level;
        this.defence = defence;
        this.mana = mana;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public int getAttack() {
        return attack;
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public int getDefence() {
        return defence;
    }

    @Override
    public int getMana() {
        return mana;
    }

    @Override
    public void addHealth(int health) {
        this.health += health;
    }

    @Override
    public void addAttack(int attack) {
        this.attack += attack;
    }

    @Override
    public void addLevel(int level) {
        this.level += level;
    }

    @Override
    public void addDefence(int defence) {
        this.defence += defence;
    }

    @Override
    public void addMana(int mana) {
        this.mana += mana;
    }
}
