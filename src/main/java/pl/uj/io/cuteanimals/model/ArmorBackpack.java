package pl.uj.io.cuteanimals.model;

import java.util.List;
import pl.uj.io.cuteanimals.model.interfaces.IEquipment;
import pl.uj.io.cuteanimals.model.interfaces.IItem;

public class ArmorBackpack implements IEquipment {
    @Override
    public List<IItem> getItems() {
        return null;
    }

    @Override
    public boolean putItem(IItem item) {
        return false;
    }

    @Override
    public boolean removeItem(IItem item) {
        return false;
    }

    @Override
    public String showItems() {
        return null;
    }
}
