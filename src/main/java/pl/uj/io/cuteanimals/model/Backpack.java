package pl.uj.io.cuteanimals.model;

import java.util.ArrayList;
import java.util.List;
import pl.uj.io.cuteanimals.model.interfaces.IEquipment;
import pl.uj.io.cuteanimals.model.interfaces.IItem;

public class Backpack implements IEquipment {
    private final List<IItem> items = new ArrayList<>();

    @Override
    public List<IItem> getItems() {
        return items;
    }

    @Override
    public boolean putItem(IItem item) {
        return items.add(item);
    }

    @Override
    public boolean removeItem(IItem item) {
        return items.remove(item);
    }

    @Override
    public String showItems() {
        return items.toString();
    }
}
