package pl.uj.io.cuteanimals.model.interfaces;

import java.util.List;
import java.util.Map;
import pl.uj.io.cuteanimals.model.ItemClass;

public interface PlayerClass {
    Map<String, IAction> getAbilities();

    List<ItemClass> getAcceptedItemClasses();
}
