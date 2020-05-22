package pl.uj.io.cuteanimals.model.interfaces;

/**
 * Encapsulates Actions' output. This should be printed to player.
 *
 * @version %I%
 * @since 0.0.1-SNAPSHOT
 */
public interface IResult {
    String getMessage();

    void setMessage(String message);
}
