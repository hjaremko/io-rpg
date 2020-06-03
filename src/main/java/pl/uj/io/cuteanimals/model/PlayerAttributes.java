package pl.uj.io.cuteanimals.model;

import pl.uj.io.cuteanimals.model.interfaces.IAttributes;
import pl.uj.io.cuteanimals.model.interfaces.ICharacter;

public class PlayerAttributes implements IAttributes {
    private final ICharacter owner;
    private int health = 100;
    private int attack = 1;
    private int level = 1;
    private int defence = 0;
    private int experience = 0;

    public PlayerAttributes(ICharacter owner) {
        this.owner = owner;
    }

    @Override
    public int getHealth() {
        return health
                + owner.getArmor()
                        .getItems()
                        .stream()
                        .mapToInt(i -> i.getAttributes().getHealth())
                        .sum();
    }

    @Override
    public int getAttack() {
        return attack
                + owner.getArmor()
                        .getItems()
                        .stream()
                        .mapToInt(i -> i.getAttributes().getAttack())
                        .sum();
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public int getDefence() {
        return defence
                + owner.getArmor()
                        .getItems()
                        .stream()
                        .mapToInt(i -> i.getAttributes().getDefence())
                        .sum();
    }

    public int getExperience() {
        return experience;
    }

    public int getRequiredExperience() {
        // TODO: some sane formula
        return this.level * 100;
    }

    public void addHealth(int health) {
        this.health += health;
    }

    public void addAttack(int attack) {
        this.attack += attack;
    }

    public void addLevel(int level) {
        this.level += level;
    }

    public void addDefence(int defence) {
        this.defence += defence;
    }

    public void addExperience(int experience) {
        var expBefore = this.experience;
        this.experience = (this.experience + experience) % getRequiredExperience();

        if (this.experience < expBefore) {
            addLevel(1);
        }
    }
}
