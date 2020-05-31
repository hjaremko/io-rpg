package pl.uj.io.cuteanimals.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;

/**
 * Provides methods to represent items table.
 *
 * @version %I%
 * @since 0.0.1-SNAPSHOT
 */
@Entity(name = "items")
public class Item {
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
                + '}';
    }
}
