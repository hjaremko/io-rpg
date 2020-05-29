package pl.uj.io.cuteanimals.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;
import pl.uj.io.cuteanimals.model.interfaces.IAttributes;
import pl.uj.io.cuteanimals.model.interfaces.IItem;

/**
 * Provides methods to represent items table.
 *
 * @version %I%
 * @since 0.0.1-SNAPSHOT
 */
@Entity(name = "items")
public class Item implements IItem {
    @JsonProperty("id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final int id;

    @JsonProperty("name")
    private final String name;

    @JsonProperty("description")
    private final String description;

    @JsonProperty("size")
    private final int size;

    @JsonProperty("attributes")
    @ManyToOne
    private final Attributes attributes;

    public Item() {
        this.id = 0;
        this.name = null;
        this.description = null;
        this.size = 0;
        this.attributes = null;
    }

    public Item(
            @JsonProperty("id") int id,
            @JsonProperty("name") String name,
            @JsonProperty("description") String description,
            @JsonProperty("size") int size,
            @JsonProperty("attributes") Attributes attributes) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.size = size;
        this.attributes = attributes;
    }

    @Override
    public String toString() {

        return "Item {"
                + "id="
                + id
                + ", name='"
                + name
                + "'"
                + ", description='"
                + description
                + "'"
                + ", size="
                + size
                + ", attributes="
                + "{health="
                + getAttributes().getHealth()
                + ", attack="
                + getAttributes().getAttack()
                + ", level="
                + getAttributes().getLevel()
                + ", defence="
                + getAttributes().getDefence()
                + "}"
                + "}";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public IAttributes getAttributes() {

        if (attributes != null) {
            return new IAttributes() {
                @Override
                public int getHealth() {
                    return attributes.getHealth();
                }

                @Override
                public int getAttack() {
                    return attributes.getAttack();
                }

                @Override
                public int getLevel() {
                    return attributes.getLevel();
                }

                @Override
                public int getDefence() {
                    return attributes.getDefence();
                }
            };
        }
        return null;
    }
}
