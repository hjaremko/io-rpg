package pl.uj.io.cuteanimals.model;

import pl.uj.io.cuteanimals.model.interfaces.IAttributes;
import pl.uj.io.cuteanimals.model.interfaces.ICharacter;

/**
 * Provides methods to manage player's attributes.
 *
 * @version %I%
 * @since 0.2.0-SNAPSHOT
 */
public class PlayerAttributes implements IAttributes {
    private final ICharacter owner;
    private int health;
    private int attack;
    private int level;
    private int defence;
    private int experience;

    public PlayerAttributes(ICharacter owner) {
        this.owner = owner;
        this.health = 100;
        this.attack = 1;
        this.level = 1;
        this.defence = 0;
        this.experience = 0;
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
