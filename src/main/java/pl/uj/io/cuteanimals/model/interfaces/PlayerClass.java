package pl.uj.io.cuteanimals.model.interfaces;

import pl.uj.io.cuteanimals.model.ItemClass;

import java.util.List;
import java.util.Map;

public interface PlayerClass {
    Map<String, IAction> getAbilities();

    List<ItemClass> getAcceptedItemClasses();

}
