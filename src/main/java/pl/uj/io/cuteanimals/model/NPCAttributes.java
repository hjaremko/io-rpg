package pl.uj.io.cuteanimals.model;

import pl.uj.io.cuteanimals.model.interfaces.IAttributes;

public class NPCAttributes implements IAttributes {
    protected int health;
    protected int attack;
    protected int level;
    protected int defence;

    public NPCAttributes(int health, int attack, int level, int defence) {
        this.health = health;
        this.attack = attack;
        this.level = level;
        this.defence = defence;
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
}
