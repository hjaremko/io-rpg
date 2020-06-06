package pl.uj.io.cuteanimals.model;

import pl.uj.io.cuteanimals.model.interfaces.ICharacter;

public class PlayerAttributes extends NPCAttributes {
    /**
     * Provides methods to manage player's attributes.
     *
     * @version %I%
     * @since 0.2.0-SNAPSHOT
     */
    private final ICharacter owner;

    private int experience;

    public PlayerAttributes(ICharacter owner) {
        super(100, 1, 1, 0);
        this.owner = owner;
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
        return super.getLevel() * 100;
    }

    public void addExperience(int experience) {
        var expBefore = this.experience;
        this.experience = (this.experience + experience) % getRequiredExperience();

        if (this.experience < expBefore) {
            addLevel(1);
        }
    }
}
