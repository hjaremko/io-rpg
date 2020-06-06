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
        int i = 1;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("You have the following items in your backpack: ").append("\n");
        for (IItem item : items) {
            stringBuilder.append(i).append(". ").append(item.getName()).append("\n");
            stringBuilder.append("Description: ").append(item.getDescription());
            stringBuilder.append(", Type: ").append(item.getType().toString());
            stringBuilder.append(", Size: ").append(item.getSize()).append(", ");
            stringBuilder.append(item.getAttributes().toString());
            stringBuilder.append("\n");
            i++;
        }

        return stringBuilder.toString();
    }
}
