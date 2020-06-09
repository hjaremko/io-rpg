package pl.uj.io.cuteanimals.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import pl.uj.io.cuteanimals.model.interfaces.IAttributes;

/**
 * Provides methods to represent attributes table.
 *
 * @version %I%
 * @since 0.0.1-SNAPSHOT
 */
@Entity(name = "attributes")
public class Attributes implements IAttributes {
    @JsonProperty("id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final int id;

    @JsonProperty("health")
    private int health;

    @JsonProperty("attack")
    private int attack;

    @JsonProperty("level")
    private int level;

    @JsonProperty("defence")
    private int defence;

    @JsonProperty("mana")
    private int mana;

    public Attributes() {
        id = 0;
        health = 0;
        attack = 0;
        level = 0;
        defence = 0;
        mana = 0;
    }

    public Attributes(
            @JsonProperty("id") int id,
            @JsonProperty("health") int health,
            @JsonProperty("attack") int attack,
            @JsonProperty("level") int level,
            @JsonProperty("defence") int defence,
            @JsonProperty("mana") int mana) {
        this.id = id;
        this.health = health;
        this.attack = attack;
        this.level = level;
        this.defence = defence;
        this.mana = mana;
    }

    @Override
    public String toString() {
        var output = "";
        output += (health != 0 ? "Health: " + health + ". " : "");
        output += (level != 0 ? "Level: " + level + ". " : "");
        output += (attack != 0 ? "Attack: " + attack + ". " : "");
        output += (defence != 0 ? "Defence: " + defence + ". " : "");
        output += (mana != 0 ? "Mana: " + mana + ". " : "");
        return output;
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
