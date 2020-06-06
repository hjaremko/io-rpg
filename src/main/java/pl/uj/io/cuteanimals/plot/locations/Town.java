package pl.uj.io.cuteanimals.plot.locations;

import pl.uj.io.cuteanimals.model.interfaces.DefaultLocation;

public class Town extends DefaultLocation {

    public Town() {
        super();
        this.description =
                "You are in your hometown. Every place is currently closed even an elegant inn, built within"
                        + "an ancient tower of rune-carved stone which usually bustling with life."
                        + "You can feel the terror in the air. Your leader has a task for you, approach him to talk to him.";
    }
}
