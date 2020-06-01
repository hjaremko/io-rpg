package pl.uj.io.cuteanimals.model;

import java.util.ArrayList;
import java.util.List;
import pl.uj.io.cuteanimals.model.interfaces.IEquipment;
import pl.uj.io.cuteanimals.model.interfaces.IItem;

public class Backpack implements IEquipment {
    private final List<IItem> backpack = new ArrayList<>();

    @Override
    public List<IItem> getItems() {
        return backpack;
    }

    @Override
    public boolean putItem(IItem item) {
        backpack.add(item);
        // TODO: check if possible
        return true;
    }

    @Override
    public boolean removeItem(IItem item) {
        backpack.remove(item);
        // TODO: check of possible (e.g. soul bounded items?)
        return true;
    }

    @Override
    public String showItems() {
        return backpack.toString();
    }
}
