package pl.uj.io.cuteanimals.model.interfaces;

import java.util.Map;

/**
 * Extends IAction interface with arguments
 *
 * @version %I%
 * @since 0.2.0-SNAPSHOT
 */
public abstract class ContainerArgumentAction<T> extends ArgumentAction {
    protected final Map<String, T> objects;

    public ContainerArgumentAction(Map<String, T> objects) {
        super();
        this.objects = objects;
    }
}
