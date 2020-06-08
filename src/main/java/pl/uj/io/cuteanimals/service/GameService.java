package pl.uj.io.cuteanimals.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.uj.io.cuteanimals.exception.InvalidCommandException;
import pl.uj.io.cuteanimals.interpreter.Expression;
import pl.uj.io.cuteanimals.interpreter.Interpreter;
import pl.uj.io.cuteanimals.model.Player;
import pl.uj.io.cuteanimals.model.WorldMap;
import pl.uj.io.cuteanimals.model.interfaces.IAction;
import pl.uj.io.cuteanimals.model.interfaces.IResult;

@Service
public class GameService {

    private static final Logger logger = LoggerFactory.getLogger(GameService.class);
    // TODO: replace with multiple players, they should be initialized after WorldMap
    private final Player player;

    private final Interpreter interpreter;

    @Autowired
    public GameService(ItemService itemService, Interpreter interpreter) {
        // TODO: use repository
        WorldMap.getInstance().initialize(itemService);
        player = new Player();
        this.interpreter = interpreter;
    }

    public IResult execute(int characterId, String command) throws InvalidCommandException {
        Expression expr =
                interpreter.parse(command, player.getCurrentLocation().getAvailableActions());
        IAction action = expr.interpret(player.getCurrentLocation().getAvailableActions());
        return action.execute(player);
    }

    public String getLocationInfo() {
        return player.getCurrentLocation().getDescription();
    }
}
