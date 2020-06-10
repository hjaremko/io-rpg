package pl.uj.io.cuteanimals.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;
import pl.uj.io.cuteanimals.model.ItemClass;
import pl.uj.io.cuteanimals.model.ItemType;
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

    @JsonProperty("type")
    @Enumerated(EnumType.STRING)
    private final ItemType type;

    @JsonProperty("itemClass")
    @Enumerated(EnumType.STRING)
    private final ItemClass itemClass;

    public Item() {
        this.id = 0;
        this.name = null;
        this.description = null;
        this.size = 0;
        this.attributes = null;
        this.type = ItemType.NEUTRAL;
        this.itemClass = ItemClass.ANY;
    }

    public Item(
            @JsonProperty("id") int id,
            @JsonProperty("name") String name,
            @JsonProperty("description") String description,
            @JsonProperty("size") int size,
            @JsonProperty("attributes") Attributes attributes,
            @JsonProperty("type") ItemType type,
            @JsonProperty("itemClass") ItemClass itemClass) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.size = size;
        this.attributes = attributes;
        this.type = type;
        this.itemClass = itemClass;
    }

    @Override
    public String toString() {
        return "Item{"
                + "id="
                + id
                + ", name='"
                + name
                + '\''
                + ", description='"
                + description
                + '\''
                + ", size="
                + size
                + ", attributes="
                + attributes
                + ", type="
                + type
                + ", itemClass="
                + itemClass
                + '}';
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
        return attributes;
    }

    @Override
    public ItemType getType() {
        return type;
    }

    @Override
    public ItemClass getItemClass() {
        return itemClass;
    }
}
