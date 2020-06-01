package pl.uj.io.cuteanimals.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.uj.io.cuteanimals.exception.InvalidCommandException;
import pl.uj.io.cuteanimals.model.Player;
import pl.uj.io.cuteanimals.model.interfaces.IAction;
import pl.uj.io.cuteanimals.model.interfaces.IResult;
import pl.uj.io.cuteanimals.model.interpreter.Expression;

@Service
public class GameService {

    private static final Logger logger = LoggerFactory.getLogger(GameService.class);
    // TODO: replace with multiple players
    Player player = new Player();

    public String execute(int characterId, Expression expr) throws InvalidCommandException {

        IAction action = expr.interpret(player.getCurrentLocation().getAvailableActions());
        IResult result = action.execute(player);
        return result.getMessage();
    }

    public String getLocationInfo() {
        return player.getCurrentLocation().getDescription();
    }
}
