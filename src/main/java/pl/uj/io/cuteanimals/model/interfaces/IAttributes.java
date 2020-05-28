package pl.uj.io.cuteanimals.model.interfaces;

/**
 * Provides methods to manage character's attributes.
 *
 * @version %I%
 * @since 0.0.1-SNAPSHOT
 */
public interface IAttributes {

    /**
     * Gives character's current health points.
     *
     * @return int type health points.
     */
    int getHealth();

    /**
     * Gives character's attack points taking account all bonuses (from weapons etc).
     *
     * @return int type summary attack points.
     */
    int getAttack();

    /**
     * Gives character's current level.
     *
     * @return int type level.
     */
    int getLevel();

    /**
     * Gives character's defence points taking account all bonuses (from shields etc).
     *
     * @return int type summary defence points.
     */
    int getDefence();
}
