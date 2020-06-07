package pl.uj.io.cuteanimals.model.fight;

import pl.uj.io.cuteanimals.model.Color;
import pl.uj.io.cuteanimals.model.Result;

public class FightLog extends Result {
    public FightLog(String message, Color color) {
        super(message, color);

        switch (color) {
            case GREEN:
                this.message = "+ " + message;
                break;
            case RED:
                this.message = "- " + message;
                break;
            case YELLOW:
            case NORMAL:
            case BOLD:
                this.message = "* " + message;
                break;
        }
    }
}
