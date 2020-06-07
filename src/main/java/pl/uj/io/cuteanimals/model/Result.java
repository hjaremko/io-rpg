package pl.uj.io.cuteanimals.model;

import pl.uj.io.cuteanimals.model.interfaces.IResult;

/**
 * Basic implementation.
 *
 * @version %I%
 * @since 0.0.1-SNAPSHOT
 */
public class Result implements IResult {
    protected String message;
    protected Color color;

    public Result(String message) {
        this.message = message;
        this.color = Color.NORMAL;
    }

    public Result(String message, Color color) {
        this.message = message;
        this.color = color;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }

    @Override
    public Color getColor() {
        return color;
    }
}
