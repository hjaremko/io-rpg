package pl.uj.io.cuteanimals.plot.locations;

import pl.uj.io.cuteanimals.model.interfaces.DefaultLocation;

public class Dungeon extends DefaultLocation {

    public Dungeon() {
        super();
        this.description =
                "You successfully pass through the bridge! Now you are in the dungeon "
                        + "and you can see nothing but the darkness. (You have to use your torch).";
    }
}
