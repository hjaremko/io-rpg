package pl.uj.io.cuteanimals.model;

import java.util.List;
import pl.uj.io.cuteanimals.model.interfaces.*;

public class Player implements ICharacter {
    ILocation currentLocation = WorldMap.getInstance().getLocation("town");

    @Override
    public List<IEquipment> getEquipment() {
        return null;
    }

    @Override
    public IAttributes getAttributes() {
        return null;
    }

    @Override
    public Result use(IAction action) {
        return null;
    }

    @Override
    public void changeLocation(ILocation where) {
        currentLocation = where;
    }

    public ILocation getCurrentLocation() {
        return currentLocation;
    }
}
