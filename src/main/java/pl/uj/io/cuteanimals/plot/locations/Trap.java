package pl.uj.io.cuteanimals.plot.locations;

import pl.uj.io.cuteanimals.model.interfaces.DefaultLocation;

public class Trap extends DefaultLocation {

    public Trap() {
        super();
        this.description =
                "Walking into the room you feel the rock "
                        + "moving under your foot. You have activated a trap!"
                        + "Poisoned arrow hits you in the arm and you feel awful "
                        + "pain. (You lose 10 health points).";
    }
}
