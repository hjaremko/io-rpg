package pl.uj.io.cuteanimals.model.exceptions;

/** Thrown in case of invalid user input */
public class InvalidCommandException extends Exception {
    public InvalidCommandException(String errorMessage) {
        super(errorMessage);
    }
}
