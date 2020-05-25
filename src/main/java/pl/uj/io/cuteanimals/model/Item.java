package pl.uj.io.cuteanimals.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import pl.uj.io.cuteanimals.model.interfaces.IAttributes;
import pl.uj.io.cuteanimals.model.interfaces.IItem;

@Entity(name = "items")
public class Item implements IItem {
    @JsonProperty("id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final int id;

    @JsonProperty("name")
    private final String name;

    public Item() {
        this.id = 0;
        this.name = null;
    }

    public Item(@JsonProperty("id") int id, @JsonProperty("name") String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Item {" + "name='" + name + "'" + ", id=" + id + "}";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public IAttributes getAttributes() {
        return null;
    }
}
