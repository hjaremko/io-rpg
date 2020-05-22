package pl.uj.io.cuteanimals.model;

import pl.uj.io.cuteanimals.model.interfaces.IResult;

/**
 * Basic implementation.
 *
 * @version %I%
 * @since 0.0.1-SNAPSHOT
 */
public class Result implements IResult {
    private String message;

    public Result(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }
}
