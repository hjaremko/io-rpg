package pl.uj.io.cuteanimals.model.interfaces;

import java.util.Map;
import pl.uj.io.cuteanimals.model.GameState;
import pl.uj.io.cuteanimals.model.WorldMap;
import pl.uj.io.cuteanimals.model.fight.FightManager;

public interface IPlayer extends ICharacter {
    /**
     * Gives result of using specific item.
     *
     * @param item specifies item to use.
     * @return Result type element.
     */
    IResult use(IItem item);

    IResult changeLocation(ILocation where);

    ILocation getCurrentLocation();

    GameState getCurrentGameState();

    void setGameState(GameState gameState);

    FightManager getFightManager();

    int takeDamage(int damage);

    Map<String, IAction> getAbilities();

    WorldMap getWorld();

    void setClass(PlayerClass playerClass);

    PlayerClass getPlayerClass();
}
