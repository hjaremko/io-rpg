package pl.uj.io.cuteanimals.model.interfaces;

import pl.uj.io.cuteanimals.model.GameState;
import pl.uj.io.cuteanimals.model.Result;

public interface IPlayer extends ICharacter {
    /**
     * Gives result of using specific action.
     *
     * @param action specifies action to execute.
     * @return Result type element.
     */
    Result use(IAction action);

    IResult changeLocation(ILocation where);

    GameState getCurrentGameState();

    void setGameState(GameState gameState);
}
